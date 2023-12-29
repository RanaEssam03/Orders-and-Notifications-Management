package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderResponse {

    @Schema(example = "500.6")
    Double total;

    public OrderResponse(Double total) {
        this.total = total;
    }
}
