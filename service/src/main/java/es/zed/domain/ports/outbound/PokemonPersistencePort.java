package es.zed.domain.ports.outbound;

import static es.zed.shared.domain.ports.OutboundPort.registerEvent;
import static es.zed.shared.domain.ports.OutboundPort.requestEvent;

import es.zed.domain.model.Pokemon;
import es.zed.domain.model.event.PokemonEvent;
import es.zed.shared.domain.model.event.Event;
import es.zed.shared.domain.ports.OutboundPort;
import io.reactivex.rxjava3.core.Single;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PokemonPersistencePort {

  public static final String GET_POKEMON_BY_ID = "getPokemonById";

  public Single<Pokemon> getPokemonById(UUID id) {
    requestEvent(GET_POKEMON_BY_ID, PokemonEvent.builder().id(id).build());
    return null;
  }
}
