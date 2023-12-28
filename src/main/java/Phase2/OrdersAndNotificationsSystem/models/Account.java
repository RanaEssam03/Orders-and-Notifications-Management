package Phase2.OrdersAndNotificationsSystem.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class Account  {

    private String username;
    @JsonIgnore // to make sure that the password is not returned in the response
    private String password;
    private String email;
    private String phoneNumber;
    private Address address;
    private double walletBalance;



    public Account(String username, String password, String email, String phoneNumber, Address address, double walletBalance) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.walletBalance=walletBalance;
    }


}
