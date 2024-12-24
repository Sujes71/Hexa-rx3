package es.zed.api.hexarx3.infrastructure.repository;

import static es.zed.api.hexarx3.domain.ports.outbound.HexaRx3PersistencePort.GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS;
import static es.zed.shared.domain.ports.outbound.OutboundPort.consumer;
import static es.zed.shared.domain.ports.outbound.OutboundPort.sendEventResponse;

import es.zed.api.hexarx3.domain.model.HexaRx3Filters;
import es.zed.api.hexarx3.infrastructure.repository.postgres.dao.HexaRx3Dao;
import es.zed.shared.domain.model.Message;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HexaRx3Repository {

  private final HexaRx3Dao hexaRx3Dao;

  public HexaRx3Repository(HexaRx3Dao hexaRx3Dao) {
    this.hexaRx3Dao = hexaRx3Dao;
  }

  @PostConstruct
  public void start() {
    consumer(GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS)
        .subscribe(this::getHexaRx3ById, throwable -> log.error("Error subscribing to consumer", throwable));
  }

  public void getHexaRx3ById(Message<?> message) {
    if (message.getBody() instanceof HexaRx3Filters) {
      HexaRx3Filters hexaRx3Filters = (HexaRx3Filters) message.getBody();
      log.info("Filter received: {}", hexaRx3Filters);

      hexaRx3Dao.findById(hexaRx3Filters.getId())
          .doOnNext(hexaRx3 -> log.info("HexaRx3 found: {}", hexaRx3))
          .doOnError(error -> log.error("Error fetching HexaRx3 from DB", error))
          .subscribe(hexaRx3 -> sendEventResponse(GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS, hexaRx3),
              error -> log.error("Error sending event response", error));
    }
  }
}