package es.zed.domain.core;

import es.zed.domain.model.HexaRx3;
import es.zed.domain.model.filter.HexaRx3Filters;
import es.zed.domain.ports.inbound.GetHexaRx3UseCase;
import es.zed.domain.ports.outbound.HexaRx3PersistencePort;
import java.util.UUID;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetHexaRx3UseCaseImpl implements GetHexaRx3UseCase {

  private final HexaRx3PersistencePort hexaRx3PersistencePort;

  public GetHexaRx3UseCaseImpl(HexaRx3PersistencePort hexaRx3PersistencePort) {
    this.hexaRx3PersistencePort = hexaRx3PersistencePort;
  }

  @Override
  public Mono<HexaRx3> execute(UUID id) {
    HexaRx3Filters hexaRx3Filters = HexaRx3Filters.builder().id(id).build();
    return Mono.from(hexaRx3PersistencePort.getHexaRx3ById(hexaRx3Filters).toFlowable());
  }
}
