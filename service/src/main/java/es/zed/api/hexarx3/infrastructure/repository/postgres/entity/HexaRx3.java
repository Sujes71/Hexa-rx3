package es.zed.api.hexarx3.infrastructure.repository.postgres.entity;

import es.zed.shared.infrastructure.repository.postgres.entity.Entity;
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
