package com.seser.mancalagame.repository;

import com.seser.mancalagame.domain.entities.PlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * @author Eser
 * This class is a player JPA repository for mongo db
 */
public interface PlayerRepository extends MongoRepository<PlayerEntity, String> {
    PlayerEntity save(PlayerEntity playerEntity);
    PlayerEntity findByUuid(UUID uuid);
}
