package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderResponse {
    /**
     * The unique identifier for the order.
     */
    @Schema(example = "2")
    Integer ID;

    /**
     * Constructs an OrderResponse with the specified order ID.
     *
     * @param ID The unique identifier for the order.
     */
    public OrderResponse(Integer ID) {
        this.ID = ID;
    }
}