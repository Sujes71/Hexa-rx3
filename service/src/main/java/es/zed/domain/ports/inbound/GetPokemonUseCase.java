package es.zed.domain.ports.inbound;

import es.zed.domain.model.Pokemon;
import es.zed.shared.UseCase;
import io.reactivex.rxjava3.core.Single;
import java.util.UUID;

public interface GetPokemonUseCase extends UseCase<UUID, Single<Pokemon>> {

}
