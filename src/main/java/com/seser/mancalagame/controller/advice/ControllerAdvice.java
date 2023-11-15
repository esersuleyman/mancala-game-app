package com.seser.mancalagame.controller.advice;

import com.seser.mancalagame.domain.exceptions.GameNotFoundException;
import com.seser.mancalagame.domain.exceptions.InvalidGameStateException;
import com.seser.mancalagame.domain.exceptions.InvalidMoveException;
import com.seser.mancalagame.domain.response.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.seser.mancalagame.utilities.Constants.RESPONSE_ERROR_MESSAGE_TEMP;

/**
 * @author Eser
 * This class contains exception handling mechanism globally for exceptions which have been defined.
 */
@RestControllerAdvice
public class ControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    /**
     * This method is exception handler for IllegalArgumentException
     * @param e
     * @return
     */
    @ExceptionHandler(value
            = IllegalArgumentException.class)
    public ResponseEntity<ResponseError> handleIllegalArgumentException(
            IllegalArgumentException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (IllegalArgumentException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .exceptionName(IllegalArgumentException.class.getName())
                .errorMessage(e.getMessage()).build();
        logger.error(RESPONSE_ERROR_MESSAGE_TEMP, errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is exception handler for GameNotFoundException
     * @param e
     * @return
     */
    @ExceptionHandler(value
            = GameNotFoundException.class)
    public ResponseEntity<ResponseError> handleGameNotFoundException(
            GameNotFoundException e) {
        String error = "Game not found! Please enter a valid game id or create a new one.";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .exceptionName(GameNotFoundException.class.getName())
                .errorMessage(e.getMessage()).build();
        logger.error(RESPONSE_ERROR_MESSAGE_TEMP, errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is exception handler for InvalidGameStateException
     * @param e
     * @return
     */
    @ExceptionHandler(value
            = InvalidGameStateException.class)
    public ResponseEntity<ResponseError> handleInvalidGameStateException(
            InvalidGameStateException e) {
        String error = "This game already ended! Please create a new game.";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .exceptionName(InvalidGameStateException.class.getName())
                .errorMessage(e.getMessage()).build();
        logger.error(RESPONSE_ERROR_MESSAGE_TEMP, errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is exception handler for InvalidMoveException
     * @param e
     * @return
     */
    @ExceptionHandler(value
            = InvalidMoveException.class)
    public ResponseEntity<ResponseError> handleInvalidMoveException(
            InvalidMoveException e) {
        String error = "Its not your turn! Wait for the opposite player!";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .exceptionName(InvalidMoveException.class.getName())
                .errorMessage(e.getMessage()).build();
        logger.error(RESPONSE_ERROR_MESSAGE_TEMP, errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
