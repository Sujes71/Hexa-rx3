package es.zed.rest;

import es.zed.domain.model.Pokemon;
import es.zed.rest.handlers.Hexarx3Handler;
import es.zed.shared.rest.HexaRxRouting;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(HexaRxRouting.BASE_URL)
public class Hexarx3Controller {

  private final Hexarx3Handler hexarx3Handler;

  public Hexarx3Controller(Hexarx3Handler hexarx3Handler) {
    this.hexarx3Handler = hexarx3Handler;
  }

  @GetMapping(path = HexaRxRouting.GET_POKEMON_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<Pokemon>> getPokemonById(@PathVariable("id") final UUID id) {
    return hexarx3Handler.handle(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
