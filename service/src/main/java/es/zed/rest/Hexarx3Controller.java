package es.zed.rest;

import es.zed.domain.model.Pokemon;
import es.zed.domain.ports.inbound.GetPokemonUseCase;
import es.zed.shared.utils.Constants;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.BASE_URL)
public class Hexarx3Controller {

  private final GetPokemonUseCase getPokemonUseCase;

  public Hexarx3Controller(GetPokemonUseCase getPokemonUseCase) {
    this.getPokemonUseCase = getPokemonUseCase;
  }

  @GetMapping(path = Constants.GET_POKEMON_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Pokemon> getPokemonById(@PathVariable("id") final UUID id) {
    return ResponseEntity.ok(getPokemonUseCase.execute(id).blockingGet());
  }
}
