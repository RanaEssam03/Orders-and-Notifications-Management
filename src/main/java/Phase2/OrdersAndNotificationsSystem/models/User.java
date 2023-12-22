package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private double walletBalance;

    public User(String username, String password, String email, String phoneNumber, String address, double walletBalance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.walletBalance = walletBalance;
    }


}
