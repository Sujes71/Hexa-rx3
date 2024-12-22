package es.zed.infrastructure.repository;

import static es.zed.domain.ports.outbound.PokemonPersistencePort.GET_POKEMON_BY_ID_REQUEST_ADDRESS;
import static es.zed.shared.domain.ports.OutboundPort.eventRegister;
import static es.zed.shared.domain.ports.OutboundPort.sendResponse;

import es.zed.domain.model.Pokemon;
import es.zed.domain.model.event.PokemonEvent;
import es.zed.shared.domain.model.event.Event;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PokemonRepository {

  @PostConstruct
  public void listener() {
    eventRegister(GET_POKEMON_BY_ID_REQUEST_ADDRESS).subscribe(this::getPokemonById);
  }

  public void getPokemonById(Event event) {
    log.info("Event received: {}", event);
    Pokemon pokemon = new Pokemon(UUID.randomUUID(), "Pikachu");
    sendResponse(GET_POKEMON_BY_ID_REQUEST_ADDRESS, new PokemonEvent(pokemon.getId(), pokemon.getName()));
  }
}