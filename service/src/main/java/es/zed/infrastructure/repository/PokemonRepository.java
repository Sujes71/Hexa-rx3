package es.zed.infrastructure.repository;

import static es.zed.domain.ports.outbound.PokemonPersistencePort.GET_POKEMON_BY_ID_REQUEST_ADDRESS;
import static es.zed.shared.domain.ports.outbound.OutboundPort.consumer;
import static es.zed.shared.domain.ports.outbound.OutboundPort.sendEventResponse;

import es.zed.domain.model.Pokemon;
import es.zed.shared.domain.model.filter.Filter;
import io.reactivex.rxjava3.core.Completable;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PokemonRepository {

  @PostConstruct
  public Completable start() {
    consumer(GET_POKEMON_BY_ID_REQUEST_ADDRESS).subscribe(this::getPokemonById);

    return Completable.complete();
  }

  public void getPokemonById(Filter filter) {
    log.info("Filter received: {}", filter);
    Pokemon pokemon = Pokemon.builder().id(UUID.randomUUID()).name("SCIZOR").build();
    sendEventResponse(GET_POKEMON_BY_ID_REQUEST_ADDRESS, pokemon);
  }
}