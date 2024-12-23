package es.zed.api.hexarx3.domain.model;

import es.zed.shared.domain.model.Filter;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class HexaRx3Filters extends Filter {

  private UUID id;
}
