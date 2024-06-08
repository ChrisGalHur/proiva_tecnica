package org.example.exception;

/**
 * Exception to handle invalid player.
 * This exception is thrown when the player is invalid.
 * Extends RuntimeException.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public class InvalidPlayerException extends RuntimeException {

    /**
     * Constructor of the class.
     * @param errorMessage The error message.
     */
    public InvalidPlayerException(String errorMessage) {
        super(errorMessage);
    }
}
