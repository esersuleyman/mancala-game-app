package com.seser.mancalagame.domain.exceptions;

/**
 * @author Eser
 * This is an custom exception class for invalid game issue.
 */
public class InvalidGameStateException extends RuntimeException {

  public InvalidGameStateException() {}
  public InvalidGameStateException(String message) {
    super(message);
  }
}