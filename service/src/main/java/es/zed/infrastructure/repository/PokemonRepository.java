package es.zed.infrastructure.repository;

import static es.zed.domain.ports.outbound.PokemonPersistencePort.GET_POKEMON_BY_ID_REQUEST;
import static es.zed.shared.domain.ports.OutboundPort.sendResponse;
import static es.zed.shared.domain.ports.OutboundPort.registerRequestEvent;
import static es.zed.shared.domain.ports.OutboundPort.requestEvent;

import es.zed.domain.model.Pokemon;
import es.zed.domain.model.event.PokemonEvent;
import es.zed.shared.domain.model.event.Event;
import io.reactivex.rxjava3.core.Single;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PokemonRepository {

  @PostConstruct
  public void listener() {
    registerRequestEvent(GET_POKEMON_BY_ID_REQUEST).subscribe(this::getPokemonById);
  }

  public void getPokemonById(Event event) {
    log.info("Event received: {}", event);
    Pokemon pokemon = new Pokemon(UUID.randomUUID(), "Pikachu");
    sendResponse(GET_POKEMON_BY_ID_REQUEST, new PokemonEvent(pokemon.getId(), pokemon.getName()));
  }
}