package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BalanceUpdateRequest {
    @Schema(example = "1000")
    Double amount;

}
