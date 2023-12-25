package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import lombok.Data;

@Data
public class BalanceUpdateRequest {
    String username;
    Double amount;

}
