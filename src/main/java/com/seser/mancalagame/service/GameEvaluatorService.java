package com.seser.mancalagame.service;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.rules.MancalaRules;
import com.seser.mancalagame.service.rules.types.LargePitRule;
import com.seser.mancalagame.service.rules.types.ResetPitRule;
import com.seser.mancalagame.service.rules.types.SmallPitRule;

/**
 * @author Eser
 * This class is an interface for game evaluator service.
 */
public interface GameEvaluatorService {
    MancalaRules[] MANCALA_RULES = { new ResetPitRule(), new SmallPitRule(), new LargePitRule() };
    boolean validateGameStatus(GameEntity gameEntity);
    void validateMove(GameMoveRequest request, int[] board);
    void evaluateScore(GameEntity gameEntity, GameMoveRequest moveRequest);
}
