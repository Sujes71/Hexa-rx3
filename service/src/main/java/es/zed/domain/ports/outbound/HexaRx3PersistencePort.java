package es.zed.domain.ports.outbound;

import static es.zed.shared.domain.ports.outbound.OutboundPort.requestEvent;

import es.zed.domain.model.HexaRx3;
import es.zed.domain.model.filter.HexaRx3Filters;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HexaRx3PersistencePort {

  public static final String GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS = "getHexaRx3ByIdRequestAddress";

  public Single<HexaRx3> getHexaRx3ById(HexaRx3Filters filters) {
    return requestEvent(GET_HEXA_RX3_BY_ID_REQUEST_ADDRESS, filters, HexaRx3.class);
  }
}