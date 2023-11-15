package com.seser.mancalagame.service;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.enums.GameStatus;
import com.seser.mancalagame.domain.exceptions.InvalidGameStateException;
import com.seser.mancalagame.domain.exceptions.InvalidMoveException;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.impl.GameEvaluatorServiceImpl;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GameEvaluatorServiceImplTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private GameEvaluatorServiceImpl service;

    @Test
    void GIVEN_not_ended_game_WHEN_validateGameStatus_called_THEN_return_true() {
        //GIVEN
        GameEntity gameEntity = dataHelper.gameEntity();
        gameEntity.setStatus(GameStatus.PROGRESS);
        //WHEN
        boolean actual = service.validateGameStatus(gameEntity);
        //THEN
        Assertions.assertEquals(true, actual);
    }

    @Test
    void GIVEN_ended_game_status_WHEN_validateGameStatus_called_THEN_throw_InvalidGameStateException() {
        //GIVEN
        GameEntity gameEntity = dataHelper.gameEntityEnd();
        //THEN
        assertThrows(InvalidGameStateException.class,
                //WHEN
                () -> service.validateGameStatus(gameEntity));
    }

    @Test
    void GIVEN_valid_move_request_WHEN_validateMove_called_THEN_should_validate_successfully() {
        //GIVEN
        GameMoveRequest gameMoveRequest = dataHelper.gameMoveRequest();
        int[] board = dataHelper.getInitBoard();
        //WHEN
        service.validateMove(gameMoveRequest, board);
        //THEN
        
    }

    @Test
    void GIVEN_not_valid_move_request_WHEN_validateMove_called_THEN_throw_InvalidMoveException() {
        //GIVEN
        GameMoveRequest gameMoveRequest = dataHelper.invalidGameMoveRequest();
        int[] board = dataHelper.getInitBoard();
        //THEN
        assertThrows(InvalidMoveException.class,
                //WHEN
                () -> service.validateMove(gameMoveRequest, board));
    }

    @Test
    void GIVEN_valid_game_entity_and_game_move_request_WHEN_evaluateScore_called_THEN_index_3_should_be_0() {
        //GIVEN
        GameEntity gameEntity = dataHelper.gameEntity();
        GameMoveRequest gameMoveRequest = dataHelper.gameMoveRequest();
        //WHEN
        service.evaluateScore(gameEntity, gameMoveRequest);
        //THEN
        Assertions.assertEquals(0, gameEntity.getBoard()[3]);
    }

    @Test
    void GIVEN_game_entity_for_end_game_move_request_WHEN_evaluateScore_called_THEN_game_should_end() {
        //GIVEN
        GameEntity gameEntity = dataHelper.gameEntityForEnd();
        GameMoveRequest gameMoveRequest = dataHelper.gameMoveRequest();
        //WHEN
        service.evaluateScore(gameEntity, gameMoveRequest);
        //THEN
        Assertions.assertEquals(GameStatus.END, gameEntity.getStatus());

    }
}
