package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Data
public class Credentials {

    @Schema(example = "ranaessam")
    private String username;
    @Schema(example = "123456")
    private String password;


}
