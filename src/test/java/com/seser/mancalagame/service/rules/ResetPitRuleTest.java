package com.seser.mancalagame.service.rules;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.rules.types.ResetPitRule;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResetPitRuleTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private ResetPitRule resetPitRule;

    @Test
    void GIVEN_a_move_reach_larger_than_totalPit_WHEN_evaluate_called_THEN_should_reset_pit_successfully() {
        //GIVEN
        GameMoveRequest gameMoveRequest = dataHelper.gameMoveRequest();
        GameEntity gameEntity = dataHelper.gameEntity();
        gameMoveRequest.setIndex(15);
        //WHEN
        resetPitRule.evaluate(gameMoveRequest,gameEntity);
        //THEN
        Assertions.assertEquals(0, gameMoveRequest.getIndex());
    }
}
