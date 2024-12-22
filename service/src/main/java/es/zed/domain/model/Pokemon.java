package es.zed.domain.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Pokemon {

  private UUID id;

  private String name;
}
