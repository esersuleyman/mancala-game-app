package com.seser.mancalagame.service.rules.types;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.rules.MancalaRules;
import com.seser.mancalagame.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Eser
 * This class contains evaluate rule for small pit number after each move.
 */
public class SmallPitRule implements MancalaRules {

    private static final Logger logger = LoggerFactory.getLogger(SmallPitRule.class);

    /**
     * This method check index is small pits for given player, updates infos and applies the rules on that.
     * @param moveRequest
     * @param gameEntity
     */
    @Override
    public void evaluate(GameMoveRequest moveRequest, GameEntity gameEntity) {
        if (moveRequest.getIndex() != Constants.FIRST_PLAYER_SCORE_PIT &&
                moveRequest.getIndex() != Constants.SECOND_PLAYER_SCORE_PIT) {
            logger.info("Evaluating small pit rule..");
            if (isValidForOpponentCapture(moveRequest, gameEntity)) {
                logger.info("Capturing opponent stones...");
                int largerPitIndex = gameHelper.getLargePitByPlayerType(moveRequest.getPlayerTurn());
                gameEntity.getBoard()[largerPitIndex] += gameHelper.captureOpponentStones(moveRequest.getIndex(), gameEntity.getBoard());
            } else {
                logger.info("Adding stones to next pits");
                ++gameEntity.getBoard()[moveRequest.getIndex()];
            }
            moveRequest.setCurrentStones(moveRequest.getCurrentStones() - 1);
        }
    }

    /**
     * This method checks if your pit had been empty and opponent pits is not empty for mancala game rule.
     * @param moveRequest
     * @param gameEntity
     * @return
     */
    private boolean isValidForOpponentCapture(GameMoveRequest moveRequest, GameEntity gameEntity) {
        return moveRequest.isPlayerBoard() &&
                moveRequest.getCurrentStones() == 1 &&
                gameEntity.getBoard()[moveRequest.getIndex()] == 0 &&
                gameEntity.getBoard()[gameHelper.getOpponentPitIndex(moveRequest.getIndex())] != 0;
    }
}
