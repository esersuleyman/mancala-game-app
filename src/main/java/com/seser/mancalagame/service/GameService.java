package com.seser.mancalagame.service;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.domain.response.GameResponse;
import com.seser.mancalagame.domain.response.GameStartResponse;

/**
 * @author Eser
 * This class is an interface for game service.
 */
public interface GameService {
    GameStartResponse start(String firstPlayer, String secondPlayer);
    GameResponse updateMove(GameMoveRequest request);
    GameEntity create(GameEntity gameEntity);
    GameResponse getGameById(String gameId);
    void delete();
}
