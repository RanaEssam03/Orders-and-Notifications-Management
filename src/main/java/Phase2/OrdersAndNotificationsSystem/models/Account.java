package Phase2.OrdersAndNotificationsSystem.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class Account {
    /**
     * The username associated with the account.
     */
    @Schema(example = "nourmuhammad")
    private String username;

    /**
     * The password associated with the account. For security reasons, it is not shown in clear text.
     */
    @Schema(example = "12345$")
    private String password;

    /**
     * The email address associated with the account.
     */
    @Schema(example = "nourmuhammad835@gmail.com")
    private String email;

    /**
     * The phone number associated with the account.
     */
    @Schema(example = "01114335538")
    private String phoneNumber;

    /**
     * The address associated with the account.
     */
    private Address address;

    /**
     * The wallet balance associated with the account.
     */
    @Schema(example = "100.0")
    private double walletBalance;

    /**
     * The chosen language for the account.
     */
    @Schema(example = "German")
    String chosenLanguage;

    /**
     * Constructs an Account with the specified parameters.
     *
     * @param username       The username associated with the account.
     * @param password       The password associated with the account.
     * @param email          The email address associated with the account.
     * @param phoneNumber    The phone number associated with the account.
     * @param address        The address associated with the account.
     * @param walletBalance  The wallet balance associated with the account.
     * @param language The chosen language for the account.
     */
    public Account(String username, String password, String email, String phoneNumber, Address address, double walletBalance, String language) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.walletBalance=walletBalance;
        this.chosenLanguage = language;
    }

    /**
     * Copy constructor. Creates a new account instance with the same attributes as the provided account.
     * The password is not copied for security reasons.
     *
     * @param account The account to copy.
     */
    public Account(Account account) {
        this.username=account.getUsername();
        this.password= "********";
        this.email=account.getEmail();
        this.phoneNumber=account.getPhoneNumber();
        this.address=account.getAddress();
        this.walletBalance=account.getWalletBalance();
        this.chosenLanguage = account.getChosenLanguage();
    }

    /**
     * Default constructor. Initializes an empty account.
     */
    public Account() {
    }
}