package com.seser.mancalagame.service.impl;


import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.enums.PlayerType;
import com.seser.mancalagame.repository.PlayerRepository;
import com.seser.mancalagame.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Eser
 * This class contains service functions for player domain.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private PlayerRepository repository;

    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }

    /**
     * This method creates a player with given information and saves to db.
     * @param playerName
     * @param playerType
     * @return
     */
    @Override
    public PlayerEntity create(String playerName, PlayerType playerType) {
        logger.debug("Creating player against name {}, with type {}", playerName, playerType);
        return repository.save(PlayerEntity
                .builder()
                .uuid(UUID.randomUUID())
                .name(playerName)
                .playerType(playerType)
                .build());
    }
}