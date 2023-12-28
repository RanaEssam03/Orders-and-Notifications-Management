package Phase2.OrdersAndNotificationsSystem.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class Account  {

    @Schema(example = "ranaessam")
    private String username;
    @Schema(example = "123456")
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
