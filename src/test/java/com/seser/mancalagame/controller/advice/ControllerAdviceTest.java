package com.seser.mancalagame.controller.advice;


import com.seser.mancalagame.domain.exceptions.GameNotFoundException;
import com.seser.mancalagame.domain.exceptions.InvalidGameStateException;
import com.seser.mancalagame.domain.exceptions.InvalidMoveException;
import com.seser.mancalagame.domain.response.ResponseError;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ControllerAdviceTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private ControllerAdvice advice;

    @Test
    void GIVEN_an_illegal_argument_exception_WHEN_handleIllegalArgumentException_called_THEN_generate_response_entity_successfully() {
        //GIVEN
        IllegalArgumentException exception = dataHelper.illegalArgumentException();
        //WHEN
        ResponseEntity<ResponseError> response = advice.handleIllegalArgumentException(exception);
        //THEN
        assertEquals("java.lang.IllegalArgumentException", response.getBody().getExceptionName());
    }

    @Test
    void GIVEN_an_game_not_found_exception_WHEN_handleGameNotFoundException_called_THEN_generate_response_entity_successfully() {
        //GIVEN
        GameNotFoundException exception = dataHelper.gameNotFoundException();
        //WHEN
        ResponseEntity<ResponseError> response = advice.handleGameNotFoundException(exception);
        //THEN
        assertEquals("com.seser.mancalagame.domain.exceptions.GameNotFoundException", response.getBody().getExceptionName());
    }

    @Test
    void GIVEN_an_invalid_game_state_exception_WHEN_handleInvalidGameStateException_called_THEN_generate_response_entity_successfully() {
        //GIVEN
        InvalidGameStateException exception = dataHelper.invalidGameStateException();
        //WHEN
        ResponseEntity<ResponseError> response = advice.handleInvalidGameStateException(exception);
        //THEN
        assertEquals("com.seser.mancalagame.domain.exceptions.InvalidGameStateException", response.getBody().getExceptionName());
    }

    @Test
    void GIVEN_an_invalid_move_exception_WHEN_handleInvalidMoveException_called_THEN_generate_response_entity_successfully() {
        //GIVEN
        InvalidMoveException exception = dataHelper.invalidMoveException();
        //WHEN
        ResponseEntity<ResponseError> response = advice.handleInvalidMoveException(exception);
        //THEN
        assertEquals("com.seser.mancalagame.domain.exceptions.InvalidMoveException", response.getBody().getExceptionName());
    }
}
