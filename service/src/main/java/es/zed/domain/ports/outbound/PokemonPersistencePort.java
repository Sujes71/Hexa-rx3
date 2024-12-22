package es.zed.domain.ports.outbound;

import static es.zed.infrastructure.repository.PokemonRepository.GET_POKEMON_BY_ID_RESPONSE;
import static es.zed.shared.domain.ports.OutboundPort.registerRequestEvent;
import static es.zed.shared.domain.ports.OutboundPort.registerResponseEvent;
import static es.zed.shared.domain.ports.OutboundPort.requestEvent;

import es.zed.domain.model.Pokemon;
import es.zed.domain.model.event.PokemonEvent;
import es.zed.shared.domain.model.event.Event;
import es.zed.shared.domain.ports.OutboundPort;
import io.reactivex.rxjava3.core.Single;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PokemonPersistencePort {

  public static final String GET_POKEMON_BY_ID_REQUEST = "getPokemonByIdRequest";

  @PostConstruct
  public void listener() {
    registerRequestEvent(GET_POKEMON_BY_ID_RESPONSE).subscribe(this::handleResponse);
  }

  public Single<Pokemon> getPokemonById(UUID id) {
    return registerResponseEvent(GET_POKEMON_BY_ID_RESPONSE)
        .doOnSubscribe(disposable -> publish(id))
        .map(event -> {
          if (event instanceof PokemonEvent pokemonEvent) {
            return new Pokemon(pokemonEvent.getId(), pokemonEvent.getName());
          }
          throw new IllegalStateException("Unexpected event type: " + event.getClass());
        });
  }

  public void publish(UUID id) {
    requestEvent(GET_POKEMON_BY_ID_REQUEST, new PokemonEvent(id, null));
  }

  private void handleResponse(Event event) {
    if (event instanceof PokemonEvent) {
      OutboundPort.handleEventResponse(GET_POKEMON_BY_ID_RESPONSE, event);
    }
  }
}
