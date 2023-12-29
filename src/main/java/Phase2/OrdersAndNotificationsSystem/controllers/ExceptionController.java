package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<?> generalException(GeneralException exception) {
        return new ResponseEntity<>(exception.getErrorData(), HttpStatus.valueOf(exception.getErrorData().status()));
    }
}
