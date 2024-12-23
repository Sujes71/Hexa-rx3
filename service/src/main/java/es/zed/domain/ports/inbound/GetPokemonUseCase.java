package es.zed.domain.ports.inbound;

import es.zed.domain.model.Pokemon;
import es.zed.shared.domain.ports.inbound.UseCase;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface GetPokemonUseCase extends UseCase<UUID, Mono<Pokemon>> {

}
