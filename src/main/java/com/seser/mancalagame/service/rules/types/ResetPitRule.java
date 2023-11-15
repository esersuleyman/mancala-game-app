package com.seser.mancalagame.service.rules.types;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.rules.MancalaRules;
import com.seser.mancalagame.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Eser
 * This class contains evaluate rule for resetting pit number after each move.
 */
public class ResetPitRule implements MancalaRules {

    private static final Logger logger = LoggerFactory.getLogger(ResetPitRule.class);

    /**
     * This method check index is bigger than total pits, resets the pit numbers.
     * @param moveRequest
     * @param gameEntity
     */
    @Override
    public void evaluate(GameMoveRequest moveRequest, GameEntity gameEntity) {
        if(moveRequest.getIndex() >= Constants.TOTAL_BOARD_PITS ) {
            logger.info("Resetting index as index exceed from limit {}", Constants.TOTAL_BOARD_PITS);
            moveRequest.setIndex(0);
        }
    }
}
