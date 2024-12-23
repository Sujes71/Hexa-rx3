package es.zed.domain.ports.outbound;

import static es.zed.shared.domain.ports.outbound.OutboundPort.requestEvent;

import es.zed.domain.model.Pokemon;
import es.zed.domain.model.filter.PokemonFilters;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PokemonPersistencePort {

  public static final String GET_POKEMON_BY_ID_REQUEST_ADDRESS = "getPokemonByIdRequestAddress";

  public Single<Pokemon> getPokemonById(PokemonFilters pokemonFilters) {
    return requestEvent(GET_POKEMON_BY_ID_REQUEST_ADDRESS, pokemonFilters, Pokemon.class);
  }
}