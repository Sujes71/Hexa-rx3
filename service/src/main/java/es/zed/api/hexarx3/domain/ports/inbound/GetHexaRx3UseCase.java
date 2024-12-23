package es.zed.api.hexarx3.domain.ports.inbound;

import es.zed.api.hexarx3.domain.model.HexaRx3Filters;
import es.zed.api.hexarx3.infrastructure.repository.postgres.entity.HexaRx3;
import es.zed.shared.domain.ports.inbound.UseCase;
import reactor.core.publisher.Mono;

public interface GetHexaRx3UseCase extends UseCase<HexaRx3Filters, Mono<HexaRx3>> {

}