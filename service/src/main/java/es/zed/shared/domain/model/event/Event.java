package es.zed.shared.domain.model.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class Event {
  String eventId;
}
