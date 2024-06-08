package org.example.exception;

/**
 * Exception to handle the case when the skin cannot be shown.
 * This exception is thrown when the skin cannot be shown.
 * Extends RuntimeException.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public class CantShowException extends RuntimeException{

    /**
     * Constructor of the class.
     * @param errorMessage The error message.
     */
    public CantShowException(String errorMessage) {
        super(errorMessage);
    }
}
