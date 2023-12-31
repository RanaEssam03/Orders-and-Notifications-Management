package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Data
public class Credentials {
    /**
     * The username for authentication.
     */
    @Schema(example = "ranaessam")
    private String username;

    /**
     * The password for authentication.
     */
    @Schema(example = "123456")
    private String password;
}
