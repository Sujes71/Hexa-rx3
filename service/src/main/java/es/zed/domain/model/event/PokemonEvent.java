package es.zed.domain.model.event;

import es.zed.shared.domain.model.event.Event;
import java.util.UUID;

public class PokemonEvent extends Event {

  private UUID id;

  private String name;

  public PokemonEvent(UUID id, String name) {
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
