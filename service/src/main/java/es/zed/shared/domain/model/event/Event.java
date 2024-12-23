package es.zed.shared.domain.model.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class Event {
  UUID eventId;

  public Event(UUID eventId) {
    this.eventId = eventId;
  }
}
