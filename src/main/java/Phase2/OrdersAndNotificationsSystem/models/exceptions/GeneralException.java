package Phase2.OrdersAndNotificationsSystem.models.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralException extends Exception {
    public record ErrorData(int status, String msg) {}

    private ErrorData errorData;
    public GeneralException(HttpStatus status, String msg) {
        this.errorData = new ErrorData( status.value()
                , msg);
    }
}
