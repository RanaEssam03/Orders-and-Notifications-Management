package Phase2.OrdersAndNotificationsSystem.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class Account  {


    @Schema(example = "nourmuhammad")
    private String username;
    @Schema(example = "12345$")
    private String password;
    @Schema(example = "nourmuhammad835@gmail.com")
    private String email;
    @Schema(example = "01114335538")
    private String phoneNumber;

    private Address address;
    @Schema(example = "100.0")
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
