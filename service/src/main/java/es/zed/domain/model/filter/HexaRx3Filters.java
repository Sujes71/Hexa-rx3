package es.zed.domain.model.filter;

import es.zed.shared.domain.model.filter.Filter;
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
