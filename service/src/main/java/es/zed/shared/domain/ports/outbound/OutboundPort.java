package es.zed.shared.domain.ports.outbound;

import es.zed.shared.domain.model.entity.Entity;
import es.zed.shared.domain.model.filter.Filter;
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

  private static final ConcurrentHashMap<String, Subject<Filter>> requests = new ConcurrentHashMap<>();
  private static final ConcurrentHashMap<String, Entity> responseBuffer = new ConcurrentHashMap<>();

  public static <T extends Entity> Single<T> requestEvent(String address, Filter filter, Class<T> clazz) {
    return requestEvent(address)
        .doOnSubscribe(disposable -> publish(address, filter))
        .map(entity -> {
          if (clazz.isInstance(entity)) {
            return clazz.cast(entity);
          } else {
            throw new ClassCastException("Cannot cast " + entity.getClass() + " to " + clazz);
          }
        });
  }

  public static Subject<Filter> consumer(String address) {
    return requests.computeIfAbsent(address, key -> PublishSubject.create());
  }

  public static void sendEventResponse(String address, Entity message) {
    responseBuffer.put(address, message);
  }

  private static Single<Entity> requestEvent(String address) {
    return Single.create((SingleEmitter<Entity> emitter) -> {
      Entity bufferedResponse = responseBuffer.remove(address);
      if (bufferedResponse != null) {
        log.info("Buffered response found for address: {}", address);
        emitter.onSuccess(bufferedResponse);
      } else {
        log.error("Error managing the response");
      }
    }).timeout(10, TimeUnit.SECONDS);
  }

  private static void publish(String address, Filter message) {
    Subject<Filter> consumer = requests.get(address);
    if (Objects.isNull(consumer)) {
      log.error("No request consumers for address {}", address);
      return;
    }
    log.info("Publishing event to address: {} with message: {}", address, message);
    consumer.onNext(message);
  }
}