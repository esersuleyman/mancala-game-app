package com.seser.mancalagame.service.rules;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.rules.types.SmallPitRule;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SmallPitRuleTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private SmallPitRule smallPitRule;

    @Test
    void GIVEN_a_valid_move_request_and_user_pit_WHEN_evaluate_called_THEN_should_act_successfully() {
        //GIVEN
        GameMoveRequest gameMoveRequest = dataHelper.gameMoveRequest();
        GameEntity gameEntity = dataHelper.gameEntity();
        gameMoveRequest.setCurrentStones(5);
        //WHEN
        smallPitRule.evaluate(gameMoveRequest,gameEntity);
        //THEN
        Assertions.assertEquals(4, gameMoveRequest.getCurrentStones());
    }
}
