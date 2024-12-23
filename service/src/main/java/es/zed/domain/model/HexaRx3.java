package es.zed.domain.model;

import es.zed.shared.domain.model.entity.Entity;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class HexaRx3 extends Entity {

  private UUID id;

  private String name;

}
