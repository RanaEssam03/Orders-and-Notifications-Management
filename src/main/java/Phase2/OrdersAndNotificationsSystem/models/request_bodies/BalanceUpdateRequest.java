package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BalanceUpdateRequest {
    /**
     * The amount by which the balance should be updated.
     */
    @Schema(example = "1000")
    Double amount;

}
