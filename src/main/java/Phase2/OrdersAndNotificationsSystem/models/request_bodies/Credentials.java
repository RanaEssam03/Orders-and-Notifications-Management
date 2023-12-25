package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import lombok.Data;
import lombok.Getter;

@Data
public class Credentials {
    @Getter
    private String username;
    private String password;


}
