package es.zed.shared.domain.ports.outbound;

import es.zed.shared.domain.model.Message;
import es.zed.shared.infrastructure.repository.postgres.entity.Entity;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutboundPort {

  private OutboundPort(){}

  private static final ConcurrentHashMap<String, Subject<Message<?>>> requests = new ConcurrentHashMap<>();
  private static final ConcurrentHashMap<String, Object> responseBuffer = new ConcurrentHashMap<>();

  public static <T> Single<T> requestEvent(String address, Object message, Class<T> clazz) {
    return requestEvent(address)
        .doOnSubscribe(disposable -> publish(address, new Message<>(message)))
        .map(response -> {
          if (clazz.isInstance(response)) {
            return clazz.cast(response);
          } else {
            throw new ClassCastException("Cannot cast " + response.getClass() + " to " + clazz);
          }
        });
  }

  public static Subject<Message<?>> consumer(String address) {
    return requests.computeIfAbsent(address, key -> PublishSubject.create());
  }

  public static void sendEventResponse(String address, Entity message) {
    responseBuffer.put(address, message);
  }

  private static Single<Object> requestEvent(String address) {
    return Single.create((SingleEmitter<Object> emitter) -> {
      Object response = responseBuffer.remove(address);
      if (response != null) {
        log.info("Buffered response found for address: {}", address);
        emitter.onSuccess(response);
      } else {
        log.error("Error managing the response");
      }
    }).timeout(10, TimeUnit.SECONDS);
  }

  private static void publish(String address, Message<?> message) {
    Subject<Message<?>> consumer = requests.get(address);
    if (Objects.isNull(consumer)) {
      log.error("No request consumers for address {}", address);
      return;
    }
    log.info("Publishing event to address: {} with message: {}", address, message);
    consumer.onNext(message);
  }
}