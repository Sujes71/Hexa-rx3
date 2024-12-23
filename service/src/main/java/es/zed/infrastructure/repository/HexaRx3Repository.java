package es.zed.infrastructure.repository;

import static es.zed.domain.ports.outbound.HexaRx3PersistencePort.GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS;
import static es.zed.shared.domain.ports.outbound.OutboundPort.consumer;
import static es.zed.shared.domain.ports.outbound.OutboundPort.sendEventResponse;

import es.zed.domain.model.HexaRx3;
import es.zed.shared.domain.model.filter.Filter;
import io.reactivex.rxjava3.core.Completable;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HexaRx3Repository {

  @PostConstruct
  public Completable start() {
    consumer(GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS).subscribe(this::getHexaRx3ById);

    return Completable.complete();
  }

  public void getHexaRx3ById(Filter filter) {
    log.info("Filter received: {}", filter);
    HexaRx3 hexaRx3 = HexaRx3.builder().id(UUID.randomUUID()).name("HexaRx3").build();
    sendEventResponse(GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS, hexaRx3);
  }
}