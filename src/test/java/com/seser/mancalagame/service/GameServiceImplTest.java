package com.seser.mancalagame.service;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.enums.GameStatus;
import com.seser.mancalagame.domain.enums.PlayerType;
import com.seser.mancalagame.domain.exceptions.GameNotFoundException;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.domain.response.GameResponse;
import com.seser.mancalagame.domain.response.GameStartResponse;
import com.seser.mancalagame.repository.GameRepository;
import com.seser.mancalagame.service.impl.GameEvaluatorServiceImpl;
import com.seser.mancalagame.service.impl.GameServiceImpl;
import com.seser.mancalagame.service.impl.PlayerServiceImpl;
import com.seser.mancalagame.utilities.DataHelper;
import com.seser.mancalagame.utilities.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private GameServiceImpl gameService;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private PlayerServiceImpl playerService;
    @Mock
    private GameEvaluatorServiceImpl gameEvaluatorService;

    @Test
    void GIVEN_created_players_WHEN_start_called_THEN_should_start_a_game() {
        //GIVEN
        PlayerEntity firstPlayer = dataHelper.firstPlayerEntity();
        PlayerEntity secondPlayer = dataHelper.firstPlayerEntity();
        when(playerService.create(anyString(),any(PlayerType.class)))
                .thenReturn(firstPlayer).thenReturn(secondPlayer);
        GameEntity gameEntity = dataHelper.gameEntity();
        GameStartResponse expected = MapperUtil.mapGameEntityToGameStartResponse(gameEntity);
        when(gameRepository.save(any(GameEntity.class)))
                .thenReturn(gameEntity);
        //WHEN
        GameStartResponse actual = gameService.start(DataHelper.FIRST_PLAYER, DataHelper.SECOND_PLAYER);
        //THEN
        verify(playerService, times(2))
                .create(anyString(),any(PlayerType.class));
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_a_valid_move_WHEN_updateMove_called_THEN_should_apply_move_action_successfully() {
        //GIVEN
        GameEntity gameEntity = dataHelper.gameEntity();
        when(gameRepository.findByUuid(any(UUID.class)))
                .thenReturn(gameEntity);
        doNothing().when(gameEvaluatorService).validateMove(any(GameMoveRequest.class),any(int[].class));
        doNothing().when(gameEvaluatorService).evaluateScore(any(GameEntity.class), any(GameMoveRequest.class));
        when(gameRepository.save(any(GameEntity.class)))
                .thenReturn(gameEntity);
        GameResponse expected = MapperUtil.mapGameEntityToGameResponse(gameEntity);
        expected.setStatus(GameStatus.PROGRESS);
        //WHEN
        GameResponse actual = gameService.updateMove(dataHelper.gameMoveRequest());
        //THEN
        verify(gameEvaluatorService, times(1))
                .validateMove(any(), any());
        verify(gameEvaluatorService, times(1))
                .evaluateScore(any(), any());
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_a_valid_game_entity_WHEN_create_called_THEN_create_or_update_successfully() {
        //GIVEN
        GameEntity expected = dataHelper.gameEntity();
        when(gameRepository.save(any(GameEntity.class)))
                .thenReturn(expected);
        //WHEN
        GameEntity actual = gameService.create(dataHelper.gameEntity());
        //THEN
        assertEquals(actual, expected);

    }

    @Test
    void GIVEN_a_valid_game_uuid_WHEN_getGameById_called_THEN_should_find_a_game() {
        //GIVEN
        GameEntity gameEntity = dataHelper.gameEntity();
        when(gameRepository.findByUuid(any(UUID.class)))
                .thenReturn(gameEntity);
        when(gameEvaluatorService.validateGameStatus(gameEntity))
                .thenReturn(true);
        GameResponse expected = MapperUtil.mapGameEntityToGameResponse(gameEntity);
        //WHEN
        GameResponse actual = gameService.getGameById(DataHelper.GAME_UUID.toString());
        //THEN
        assertEquals(actual, expected);
        verify(gameEvaluatorService, times(1))
                .validateGameStatus(dataHelper.gameEntity());
    }

    @Test
    void GIVEN_a_not_valid_game_uuid_WHEN_getGameById_called_THEN_throw_an_GameNotFoundException() {
        //GIVEN
        when(gameRepository.findByUuid(any(UUID.class)))
                .thenReturn(null);
        //THEN
        assertThrows(GameNotFoundException.class, () ->
            //WHEN
            gameService.getGameById(DataHelper.GAME_UUID.toString())
        );
    }

    @Test
    void GIVEN_a_game_service_WHEN_delete_called_THEN_should_delete_all_games() {
        //GIVEN
        doNothing().when(gameRepository).deleteAll();
        //WHEN
        gameService.delete();
        //THEN
    }
}
