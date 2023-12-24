package Phase2.OrdersAndNotificationsSystem.models;
import lombok.Data;


@Data
public class Account {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Address address;
    private double walletBalance;

}
