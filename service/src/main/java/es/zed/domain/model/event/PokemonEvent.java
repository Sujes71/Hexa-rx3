package es.zed.domain.model.event;

import es.zed.shared.domain.model.event.Event;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PokemonEvent extends Event {

  private UUID id;

  private String name;


}
