package org.example.exception;

/**
 * Exception to handle invalid purchase.
 * This exception is thrown when the purchase is invalid.
 * Extends RuntimeException.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public class InvalidPurchaseException extends RuntimeException{

    /**
     * Constructor of the class.
     * @param errorMessage The error message.
     */
    public InvalidPurchaseException(String errorMessage) {
        super(errorMessage);
    }
}
