package es.zed.domain.ports.outbound;

import static es.zed.shared.domain.ports.OutboundPort.publishEvent;
import static es.zed.shared.domain.ports.OutboundPort.requestEvent;

import es.zed.domain.model.Pokemon;
import es.zed.domain.model.event.PokemonEvent;
import io.reactivex.rxjava3.core.Single;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PokemonPersistencePort {

  public static final String GET_POKEMON_BY_ID_REQUEST_ADDRESS = "getPokemonByIdRequestAddress";

  public Single<Pokemon> getPokemonById(UUID id) {
    return requestEvent(GET_POKEMON_BY_ID_REQUEST_ADDRESS)
        .doOnSubscribe(disposable -> publishEvent(GET_POKEMON_BY_ID_REQUEST_ADDRESS, new PokemonEvent(id, null)))
        .map(event -> {
          if (event instanceof PokemonEvent pokemonEvent) {
            return new Pokemon(pokemonEvent.getId(), pokemonEvent.getName());
          }
          throw new IllegalStateException("Unexpected event type: " + event.getClass());
        });
  }
}
