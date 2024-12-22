package es.zed.domain.model.event;

import es.zed.shared.domain.model.event.Event;
import java.util.UUID;
import lombok.Builder;

@Builder
public class PokemonEvent extends Event {

  private UUID id;

  private String name;

}
