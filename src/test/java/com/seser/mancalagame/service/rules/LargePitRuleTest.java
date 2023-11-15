package com.seser.mancalagame.service.rules;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.enums.PlayerType;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.rules.types.LargePitRule;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LargePitRuleTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private LargePitRule largePitRule;

    @Test
    void GIVEN_finalize_on_score_pit_WHEN_evaluate_called_THEN_should_gain_one_more_move() {
        //GIVEN
        GameMoveRequest gameMoveRequest = dataHelper.gameMoveRequest();
        gameMoveRequest.setIndex(13);
        gameMoveRequest.setPlayerTurn(PlayerType.SECOND);
        gameMoveRequest.setCurrentStones(1);
        GameEntity gameEntity = dataHelper.gameEntity();
        //WHEN
        largePitRule.evaluate(gameMoveRequest,gameEntity);
        //THEN
        Assertions.assertEquals(true, gameMoveRequest.isFinalOnLargePit());
    }
}
