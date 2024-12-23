package es.zed.api.hexarx3.rest.adapter;

import es.zed.api.hexarx3.domain.model.HexaRx3Filters;
import java.util.UUID;

public class HexaRx3FilterAdapter {

  public static HexaRx3Filters adapt(UUID id) {
    return HexaRx3Filters.builder().id(id).build();
  }
}
