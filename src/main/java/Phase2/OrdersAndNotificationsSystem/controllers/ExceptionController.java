package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller advice class for handling custom exceptions in the application.
 * It provides a global exception handling mechanism for the controllers.
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Handles instances of GeneralException and returns an appropriate ResponseEntity.
     *
     * @param exception The GeneralException instance to be handled.
     * @return A ResponseEntity containing the error data and HTTP status code.
     */
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<?> generalException(GeneralException exception) {
        return new ResponseEntity<>(exception.getErrorData(), HttpStatus.valueOf(exception.getErrorData().status()));
    }
}
