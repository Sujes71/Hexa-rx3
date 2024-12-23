package es.zed.domain.ports.inbound;

import es.zed.domain.model.HexaRx3;
import es.zed.shared.domain.ports.inbound.UseCase;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface GetHexaRx3UseCase extends UseCase<UUID, Mono<HexaRx3>> {

}
