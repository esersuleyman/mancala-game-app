package com.seser.mancalagame.repository;

import com.seser.mancalagame.domain.entities.GameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * @author Eser
 * This class is a game JPA repository for mongo db
 */
public interface GameRepository extends MongoRepository<GameEntity, String> {
    GameEntity save(GameEntity gameEntity);
    GameEntity findByUuid(UUID uuid);
}
