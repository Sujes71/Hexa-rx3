package es.zed.rest.handlers;

import es.zed.domain.model.Pokemon;
import es.zed.domain.ports.inbound.GetPokemonUseCase;
import es.zed.shared.rest.handlers.Handler;
import java.util.UUID;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Hexarx3Handler implements Handler<Mono<Pokemon>, UUID> {

  private final GetPokemonUseCase getPokemonUseCase;

  public Hexarx3Handler(GetPokemonUseCase getPokemonUseCase) {
    this.getPokemonUseCase = getPokemonUseCase;
  }

  @Override
  public Mono<Pokemon> handle(UUID id) {
    return getPokemonUseCase.execute(id);
  }
}
