package com.seser.mancalagame.service.rules;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.utilities.GameHelper;

/**
 * @author Eser
 * This class is an interface for rule services.
 */
public interface MancalaRules {
    GameHelper gameHelper = GameHelper.getInstance();
    void evaluate(GameMoveRequest moveRequest, GameEntity gameEntity);
}
