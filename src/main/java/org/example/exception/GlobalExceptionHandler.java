package org.example.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class is responsible for handling the exceptions thrown by the application.
 * - Handles the InvalidPlayerException.
 * - Handles the InvalidPurchaseException.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //region PLAYER
    /**
     * Handles the InvalidPlayerException.
     * @param e The exception to handle the invalid player.
     * @return The message for the invalid player.
     */
    @ExceptionHandler(value = InvalidPlayerException.class)
    public String handleInvalidPlayerException(InvalidPlayerException e) {
        return "Invalid player";
    }
    //endregion PLAYER

    //region SKIN
    /**
     * Handles the InvalidPurchaseException.
     * @param e The exception to handle the invalid purchase of a skin.
     * @return The message for the invalid purchase.
     */
    @ExceptionHandler(value = InvalidPurchaseException.class)
    public String handleInvalidPurchaseException(InvalidPurchaseException e) {
        return "Invalid purchase";
    }
    //endregion SKIN
}