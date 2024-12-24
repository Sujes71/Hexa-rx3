package es.zed.shared.domain.ports.outbound;

import es.zed.shared.domain.model.Message;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OutboundPort {

  private OutboundPort(){}

  private static final ConcurrentHashMap<String, Subject<Message<?>>> requests = new ConcurrentHashMap<>();

  public static <T> Single<T> requestEvent(String address, Object message, Class<T> clazz) {
    return requestEvent(address)
        .doOnSubscribe(disposable -> publish(address, new Message<>(message)))
        .flatMap(response -> {
          if (clazz.isInstance(response)) {
            return Single.just(clazz.cast(response));
          } else {
            return Single.error(new ClassCastException("Cannot cast " + response.getClass() + " to " + clazz));
          }
        });
  }

  private static Single<Object> requestEvent(String address) {
    return Single.create(emitter -> {
      Subject<Message<?>> consumer = requests.get(address);
      if (consumer != null) {
        consumer.firstOrError()
            .timeout(10, TimeUnit.SECONDS)
            .subscribe(
                message -> {
                  Object response = message.getBody();
                  log.info("Received response for address: {}", address);
                  emitter.onSuccess(response);
                },
                throwable -> {
                  log.error("Error processing the response", throwable);
                  emitter.onError(throwable);
                }
            );
      } else {
        log.error("No consumer found for address {}", address);
        emitter.onError(new IllegalStateException("No consumer found for address " + address));
      }
    });
  }

  public static Subject<Message<?>> consumer(String address) {
    return requests.computeIfAbsent(address, key -> PublishSubject.create());
  }

  public static void sendEventResponse(String address, Object message) {
    Subject<Message<?>> subject = requests.get(address);
    if (subject != null) {
      log.info("Sending event response to address: {} with message: {}", address, message);
      subject.onNext(new Message<>(message));
      subject.firstOrError()
          .blockingSubscribe(
              msg -> log.info("Response consumed for address: {}", address),
              error -> log.error("Error while processing response for address: {}", address, error)
          );
    } else {
      log.error("No consumers registered for address: {}", address);
    }
  }

  private static synchronized void publish(String address, Message<?> message) {
    Subject<Message<?>> consumer = requests.get(address);
    if (Objects.isNull(consumer)) {
      log.error("No request consumers for address {}", address);
      return;
    }
    log.info("Publishing event to address: {} with message: {}", address, message);
    consumer.onNext(message);
  }
}