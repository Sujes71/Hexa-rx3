package es.zed.api.hexarx3.domain.core;

import es.zed.api.hexarx3.domain.model.HexaRx3Filters;
import es.zed.api.hexarx3.domain.ports.inbound.GetHexaRx3UseCase;
import es.zed.api.hexarx3.domain.ports.outbound.HexaRx3PersistencePort;
import es.zed.api.hexarx3.infrastructure.repository.postgres.entity.HexaRx3;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetHexaRx3UseCaseImpl implements GetHexaRx3UseCase {

  private final HexaRx3PersistencePort hexaRx3PersistencePort;

  public GetHexaRx3UseCaseImpl(HexaRx3PersistencePort hexaRx3PersistencePort) {
    this.hexaRx3PersistencePort = hexaRx3PersistencePort;
  }

  @Override
  public Mono<HexaRx3> execute(HexaRx3Filters hexaRx3Filters) {
    return Mono.from(hexaRx3PersistencePort.getHexaRx3ById(hexaRx3Filters).toFlowable());
  }
}
