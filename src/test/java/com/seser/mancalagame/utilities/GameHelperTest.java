package com.seser.mancalagame.utilities;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.enums.PlayerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GameHelperTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private GameHelper gameHelper;

    @Test
    void GIVEN_inital_board_WHEN_getInitialBoard_called_THEN_should_return_an_initial_board() {
        //GIVEN
        int[] expected = dataHelper.getInitBoard();
        //WHEN
        int[] actual = gameHelper.getInitialBoard();
        //THEN
        assertArrayEquals(expected, actual);

    }

    @Test
    void GIVEN_first_player_WHEN_getLargePitByPlayerType_called_THEN_should_return_first_player() {
        //GIVEN
        PlayerType firstPlayer = PlayerType.FIRST;
        int expected = 6;
        //WHEN
        int actual = gameHelper.getLargePitByPlayerType(firstPlayer);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_first_player_WHEN_maxIndexByPlayerType_called_THEN_should_return_max_index_for_first_player() {
        //GIVEN
        PlayerType firstPlayer = PlayerType.FIRST;
        int expected = 5;
        //WHEN
        int actual = gameHelper.maxIndexByPlayerType(firstPlayer);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_first_player_WHEN_minIndexByPlayerType_called_THEN_should_return_min_index_for_first_player() {
        //GIVEN
        PlayerType firstPlayer = PlayerType.FIRST;
        int expected = 0;
        //WHEN
        int actual = gameHelper.minIndexByPlayerType(firstPlayer);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_current_index_WHEN_getOpponentPitIndex_called_THEN_should_return_opponent_pit_index() {
        //GIVEN
        int currentIndex = 0;
        int expected = 12;
        //WHEN
        int actual = gameHelper.getOpponentPitIndex(currentIndex);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_next_index_and_board_WHEN_captureOpponentStones_called_THEN_should_capture_opponent_stones() {
        //GIVEN
        int[] board = DataHelper.BOARD;
        int nextIndex = 2;
        int expected = 7;
        //WHEN
        int actual = gameHelper.captureOpponentStones(nextIndex,board);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_second_player_type_WHEN_getOpponentByType_called_THEN_should_return_opponent_player_type() {
        //GIVEN
        PlayerType secondPlayer = PlayerType.SECOND;
        //WHEN
        PlayerType oppositePlayer = gameHelper.getOpponentByType(secondPlayer);
        //THEN
        assertEquals(PlayerType.FIRST, oppositePlayer);
    }

    @Test
    void GIVEN_game_entity_and_second_player_type_WHEN_getPlayerFromGameEntityByType_called_THEN_should_return_second_player() {
        //GIVEN
        PlayerType secondPlayer = PlayerType.SECOND;
        GameEntity gameEntity = dataHelper.gameEntity();
        //WHEN
        PlayerEntity actual = gameHelper.getPlayerFromGameEntityByType(gameEntity, secondPlayer);
        //THEN
        assertEquals(dataHelper.secondPlayerEntity(), actual);
    }

    @Test
    void GIVEN_player_and_board_WHEN_collectStones_called_THEN_should_return_total_stones() {
        //GIVEN
        PlayerType secondPlayer = PlayerType.SECOND;
        int[] board = dataHelper.getInitBoard();
        int expected = 36;
        //WHEN
        int actual = gameHelper.collectStones(secondPlayer, board);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_player_type_board_and_number_WHEN_fillBoard_called_THEN_should_fill_board_with_given_number() {
        //GIVEN
        PlayerType secondPlayer = PlayerType.SECOND;
        int[] board = dataHelper.getInitBoard();
        int expected = 5;
        //WHEN
        gameHelper.fillBoard(secondPlayer, board, expected);
        //THEN
        assertEquals(expected, board[7]);
    }

    @Test
    void GIVEN_board_WHEN_anyEmptyBoard_called_THEN_should_be_empty_board() {
        //GIVEN
        int[] board = dataHelper.getInitBoardForEnd();
        boolean expected = true;
        //WHEN
        boolean actual = gameHelper.anyEmptyBoard(board);
        //THEN
        assertEquals(expected, actual);
    }
}
