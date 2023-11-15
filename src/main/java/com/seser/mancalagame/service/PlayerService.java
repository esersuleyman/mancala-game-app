package com.seser.mancalagame.service;


import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.enums.PlayerType;

/**
 * @author Eser
 * This class is an interface for player service.
 */
public interface PlayerService {
    PlayerEntity create(String playerName, PlayerType playerType);
}
