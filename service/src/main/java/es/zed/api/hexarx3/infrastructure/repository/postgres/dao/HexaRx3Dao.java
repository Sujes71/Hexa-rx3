package es.zed.api.hexarx3.infrastructure.repository.postgres.dao;

import es.zed.api.hexarx3.infrastructure.repository.postgres.entity.HexaRx3;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HexaRx3Dao extends ReactiveCrudRepository<HexaRx3, UUID> {

}
