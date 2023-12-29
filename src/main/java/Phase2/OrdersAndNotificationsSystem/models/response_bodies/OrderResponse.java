package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderResponse {

    @Schema(example = "2")
    Integer ID;

    public OrderResponse(Integer ID) {
        this.ID = ID;
    }
}
