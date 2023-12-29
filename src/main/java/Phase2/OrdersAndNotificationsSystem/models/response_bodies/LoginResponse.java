package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResponse {
    @Schema(example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub3VybXVoYW1tYWQiLCJleHAiOjE2MjIwNjUwNjYsImlhdCI6MTYyMjA2NDI2")
    String token;
    public LoginResponse(String token) {
        this.token = token;
    }
}
