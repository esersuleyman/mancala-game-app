package com.seser.mancalagame.domain.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlayerTypeTest {

    @Test
    void GIVEN_first_player_type_WHEN_assigning_THEN_return_successfully() {
        //GIVEN
        PlayerType playerType = PlayerType.FIRST;
        //WHEN THEN
        assertEquals("FIRST", playerType.toString());
    }

    @Test
    void GIVEN_second_player_type_WHEN_assigning_THEN_return_successfully() {
        //GIVEN
        PlayerType playerType = PlayerType.SECOND;
        //WHEN THEN
        assertEquals("SECOND", playerType.toString());
    }

}
