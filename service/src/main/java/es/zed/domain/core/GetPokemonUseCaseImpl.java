package es.zed.domain.core;

import es.zed.domain.model.Pokemon;
import es.zed.domain.ports.inbound.GetPokemonUseCase;
import es.zed.domain.ports.outbound.PokemonPersistencePort;
import io.reactivex.rxjava3.core.Single;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GetPokemonUseCaseImpl implements GetPokemonUseCase {

  private final PokemonPersistencePort pokemonPersistencePort;

  public GetPokemonUseCaseImpl(PokemonPersistencePort pokemonPersistencePort) {
    this.pokemonPersistencePort = pokemonPersistencePort;
  }

  @Override
  public Single<Pokemon> execute(UUID id) {
    return pokemonPersistencePort.getPokemonById(id);
  }
}