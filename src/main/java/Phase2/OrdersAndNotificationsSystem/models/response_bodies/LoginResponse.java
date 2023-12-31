package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResponse {
    /**
     * The authentication token generated upon successful login.
     */
    @Schema(example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub3VybXVoYW1tYWQiLCJleHAiOjE2MjIwNjUwNjYsImlhdCI6MTYyMjA2NDI2")
    String token;

    /**
     * Constructs a login response with the provided authentication token.
     *
     * @param token The authentication token generated upon successful login.
     */
    public LoginResponse(String token) {
        this.token = token;
    }
}