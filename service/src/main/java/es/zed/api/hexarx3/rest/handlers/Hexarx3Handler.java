package es.zed.api.hexarx3.rest.handlers;

import es.zed.api.hexarx3.domain.model.HexaRx3Filters;
import es.zed.api.hexarx3.domain.ports.inbound.GetHexaRx3UseCase;
import es.zed.api.hexarx3.infrastructure.repository.postgres.entity.HexaRx3;
import es.zed.shared.rest.handlers.Handler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Hexarx3Handler implements Handler<HexaRx3Filters, Mono<HexaRx3>> {

  private final GetHexaRx3UseCase getHexaRx3UseCase;

  public Hexarx3Handler(GetHexaRx3UseCase getHexaRx3UseCase) {
    this.getHexaRx3UseCase = getHexaRx3UseCase;
  }

  @Override
  public Mono<HexaRx3> handle(HexaRx3Filters hexaRx3Filters) {
    return getHexaRx3UseCase.execute(hexaRx3Filters);
  }
}
