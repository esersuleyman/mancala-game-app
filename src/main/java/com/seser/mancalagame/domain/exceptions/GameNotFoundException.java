package com.seser.mancalagame.domain.exceptions;

/**
 * @author Eser
 * This is an custom exception class for game not found issue.
 */
public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {}
    public GameNotFoundException(String message) {
        super(message);
    }
}
