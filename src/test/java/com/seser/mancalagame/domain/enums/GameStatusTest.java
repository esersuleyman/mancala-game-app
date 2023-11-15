package com.seser.mancalagame.domain.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GameStatusTest {

    @Test
    void GIVEN_start_game_status_WHEN_assigning_THEN_return_successfully() {
        //GIVEN
        GameStatus startStatus = GameStatus.START;
        //WHEN THEN
        assertEquals("START", startStatus.toString());
    }

    @Test
    void GIVEN_progress_game_status_WHEN_assigning_THEN_return_successfully() {
        //GIVEN
        GameStatus progressStatus = GameStatus.PROGRESS;
        //WHEN THEN
        assertEquals("PROGRESS", progressStatus.toString());
    }

    @Test
    void GIVEN_end_game_status_WHEN_assigning_THEN_return_successfully() {
        //GIVEN
        GameStatus endStatus = GameStatus.END;
        //WHEN THEN
        assertEquals("END", endStatus.toString());
    }
}
