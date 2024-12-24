package es.zed.api.hexarx3.infrastructure.repository.postgres.entity;

import es.zed.shared.infrastructure.repository.postgres.entity.Entity;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@Table("HexaRx3")
public class HexaRx3 extends Entity {

  @Id
  private UUID id;

  @Column("name")
  private String name;

}
