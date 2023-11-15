package com.seser.mancalagame.service.rules.types;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.rules.MancalaRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Eser
 * This class contains evaluate rule for large pit after each move.
 */
public class LargePitRule implements MancalaRules {

    private static final Logger logger = LoggerFactory.getLogger(LargePitRule.class);

    /**
     * This method check index is larger pit for given player, updates infos and applies the rules on that.
     * @param moveRequest
     * @param gameEntity
     */
    @Override
    public void evaluate(GameMoveRequest moveRequest, GameEntity gameEntity) {
        int largerPitIndex = gameHelper.getLargePitByPlayerType(moveRequest.getPlayerTurn());
        if (moveRequest.getIndex() == largerPitIndex) {
            logger.info("Evaluating large pit rule..");
            ++gameEntity.getBoard()[largerPitIndex];
            moveRequest.setCurrentStones(moveRequest.getCurrentStones() - 1);
            if (moveRequest.getCurrentStones() == 0) {
                logger.info("Final move exists on larger pit");
                moveRequest.setFinalOnLargePit(Boolean.TRUE);
            }
            moveRequest.setPlayerBoard(!moveRequest.isPlayerBoard());
        }

    }
}
