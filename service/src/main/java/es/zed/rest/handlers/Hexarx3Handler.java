package es.zed.rest.handlers;

import es.zed.domain.model.HexaRx3;
import es.zed.domain.ports.inbound.GetHexaRx3UseCase;
import es.zed.shared.rest.handlers.Handler;
import java.util.UUID;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Hexarx3Handler implements Handler<Mono<HexaRx3>, UUID> {

  private final GetHexaRx3UseCase getHexaRx3UseCase;

  public Hexarx3Handler(GetHexaRx3UseCase getHexaRx3UseCase) {
    this.getHexaRx3UseCase = getHexaRx3UseCase;
  }

  @Override
  public Mono<HexaRx3> handle(UUID id) {
    return getHexaRx3UseCase.execute(id);
  }
}
