package es.zed.infrastructure.repository;

import static es.zed.domain.ports.outbound.PokemonPersistencePort.GET_POKEMON_BY_ID;
import static es.zed.shared.domain.ports.OutboundPort.registerEvent;
import static es.zed.shared.domain.ports.OutboundPort.requestEvent;

import es.zed.domain.model.Pokemon;
import es.zed.shared.domain.model.event.Event;
import io.reactivex.rxjava3.core.Single;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PokemonRepository {

  @PostConstruct
  public void registerConsummers() {
    registerEvent(GET_POKEMON_BY_ID).subscribe(this::getPokemonById);
  }

  public Single<Pokemon> getPokemonById(Event event) {
    log.info("Event received {}", event);
    return null;
  }
}
