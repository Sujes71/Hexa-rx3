package es.zed.shared.domain.ports;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.zed.shared.domain.model.event.Event;
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

  private static final ConcurrentHashMap<String, Subject<Event>> requests = new ConcurrentHashMap<>();
  private static final ConcurrentHashMap<String, Event> responseBuffer = new ConcurrentHashMap<>();

  public static void requestEvent(String address, Event message) {
    Subject<Event> consumer = requests.get(address);
    if (Objects.isNull(consumer)) {
      log.error("No request consumers for address {}", address);
      return;
    }
    log.info("Publishing event to address: {} with message: {}", address, message);
    consumer.onNext(message);
  }

  public static void sendResponse(String address, Event message) {
    Subject<Event> consumer = requests.get(address);
    if (Objects.isNull(consumer)) {
      log.error("No request consumers to update for address {}", address);
      return;
    }
    responseBuffer.put(address, message);
  }


  public static Subject<Event> registerRequestEvent(String address) {
    return requests.computeIfAbsent(address, key -> PublishSubject.create());
  }

  public static Single<Event> registerResponseEvent(String address) {
    return Single.create((SingleEmitter<Event> emitter) -> {
      Event bufferedResponse = responseBuffer.remove(address);
      if (bufferedResponse != null) {
        log.info("Buffered response found for address: {}", address);
        emitter.onSuccess(bufferedResponse);
      } else {
        log.error("Error managing the response");
      }
    }).timeout(10, TimeUnit.SECONDS);
  }

}