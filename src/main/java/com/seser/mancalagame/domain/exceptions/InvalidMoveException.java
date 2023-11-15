package com.seser.mancalagame.domain.exceptions;

/**
 * @author Eser
 * This is an custom exception class for invalid move issue.
 */
public class InvalidMoveException extends RuntimeException {

  public InvalidMoveException() {}
  public InvalidMoveException(String message) {
    super(message);
  }
}
