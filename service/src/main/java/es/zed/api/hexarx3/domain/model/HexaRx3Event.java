package es.zed.api.hexarx3.domain.model;

import es.zed.shared.domain.model.Event;
import java.util.UUID;

public class HexaRx3Event extends Event {

  private UUID id;

  private String name;

  public HexaRx3Event(UUID id, String name) {
    super(UUID.randomUUID());
    this.id = id;
    this.name = name;
  }

  @Override
  public UUID getEventId() {
    return super.getEventId();
  }

  public String getName() {
    return name;
  }

  public UUID getId() {
    return id;
  }
}
