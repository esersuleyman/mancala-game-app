package com.seser.mancalagame.controller;


import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.domain.response.GameResponse;
import com.seser.mancalagame.domain.response.GameStartResponse;
import com.seser.mancalagame.service.impl.GameServiceImpl;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.seser.mancalagame.utilities.Constants.GAME_RESPONSE_DATA_KEY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameServiceImpl gameService;

    @Test
    void GIVEN_uuid_WHEN_gamePlay_called_THEN_should_return_a_game_response() {
        //GIVEN
        GameResponse expected = dataHelper.gameResponse();
        when(gameService.getGameById(any(String.class)))
                .thenReturn(expected);
        //WHEN
        GameResponse actual = (GameResponse) gameController.gamePlay(dataHelper.gamePlayRequest()).getModel().get(GAME_RESPONSE_DATA_KEY);
        //THEN
        verify(gameService, times(1)).getGameById(any(String.class));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void GIVEN_GameStartRequest_WHEN_start_called_THEN__should_return_a_game_start_response() {
        //GIVEN
        GameStartResponse expected = dataHelper.gameStartResponse();
        when(gameService.start(any(String.class), any(String.class)))
                .thenReturn(expected);
        //WHEN
        GameStartResponse actual = gameController.start(dataHelper.gameStartRequest());
        //THEN
        verify(gameService, times(1)).start(dataHelper.gameStartRequest().getFirstPlayer(), dataHelper.gameStartRequest().getSecondPlayer());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void GIVEN_GameMoveRequest_WHEN_updateMove_called_THEN_should_return_a_game_response() {
        //GIVEN
        GameResponse expected = dataHelper.gameResponse();
        when(gameService.updateMove(any(GameMoveRequest.class)))
                .thenReturn(expected);
        //WHEN
        GameResponse actual = gameController.updateMove(dataHelper.gameMoveRequest());
        //THEN
        verify(gameService, times(1)).updateMove(dataHelper.gameMoveRequest());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void GIVEN_uuid_WHEN_getGameById_called_THEN_should_return_a_game_response() {
        //GIVEN
        GameResponse expected = dataHelper.gameResponse();
        when(gameService.getGameById(anyString()))
                .thenReturn(expected);
        //WHEN
        GameResponse actual = gameController.getGameById(DataHelper.GAME_UUID.toString());
        //THEN
        verify(gameService, times(1)).getGameById(any(String.class));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void GIVEN_gameController_WHEN_delete_called_THEN_should_delete_all_games() {
        //GIVEN
        doNothing().when(gameService).delete();
        //WHEN
        gameController.delete();
        //THEN
        verify(gameService, times(1)).delete();
    }
}
