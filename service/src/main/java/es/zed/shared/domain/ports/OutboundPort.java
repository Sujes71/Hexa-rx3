package es.zed.shared.domain.ports;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.zed.shared.domain.model.event.Event;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutboundPort {

  private static final ConcurrentHashMap<String, Subject<Event>> consumers = new ConcurrentHashMap<>();

  public static void requestEvent(String address, Event message) {
    Subject<Event> consumer = consumers.get(address);
    if (Objects.isNull(consumer)) {
      log.error("No consumers for address {}", address);
    }
    consumer.onNext(message);
  }

  public static Subject<Event> registerEvent(String address) {
    return consumers.computeIfAbsent(address, key -> PublishSubject.create());
  }
}
