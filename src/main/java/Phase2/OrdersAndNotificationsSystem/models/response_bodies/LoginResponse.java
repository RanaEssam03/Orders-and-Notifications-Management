package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

public class LoginResponse {
    String token;
    String username;
    String role;

    public LoginResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }
}
