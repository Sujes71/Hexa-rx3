package es.zed.domain.core;

import es.zed.domain.model.Pokemon;
import es.zed.domain.model.filter.PokemonFilters;
import es.zed.domain.ports.inbound.GetPokemonUseCase;
import es.zed.domain.ports.outbound.PokemonPersistencePort;
import java.util.UUID;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetPokemonUseCaseImpl implements GetPokemonUseCase {

  private final PokemonPersistencePort pokemonPersistencePort;

  public GetPokemonUseCaseImpl(PokemonPersistencePort pokemonPersistencePort) {
    this.pokemonPersistencePort = pokemonPersistencePort;
  }

  @Override
  public Mono<Pokemon> execute(UUID id) {
    PokemonFilters pokemonFilters = PokemonFilters.builder().id(id).build();
    return Mono.from(pokemonPersistencePort.getPokemonById(pokemonFilters).toFlowable());
  }
}
