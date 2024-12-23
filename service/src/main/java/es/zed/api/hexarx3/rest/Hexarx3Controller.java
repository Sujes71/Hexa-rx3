package es.zed.api.hexarx3.rest;

import es.zed.api.hexarx3.infrastructure.repository.postgres.entity.HexaRx3;
import es.zed.api.hexarx3.rest.adapter.HexaRx3FilterAdapter;
import es.zed.api.hexarx3.rest.handlers.Hexarx3Handler;
import es.zed.shared.rest.HexaRx3Routing;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(HexaRx3Routing.BASE_URL)
public class Hexarx3Controller {

  private final Hexarx3Handler hexarx3Handler;

  public Hexarx3Controller(Hexarx3Handler hexarx3Handler) {
    this.hexarx3Handler = hexarx3Handler;
  }

  @GetMapping(path = HexaRx3Routing.GET_HEXA_RX3_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<HexaRx3>> getHexaRx3ById(@PathVariable("id") final UUID id) {
    return hexarx3Handler.handle(HexaRx3FilterAdapter.adapt(id))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
