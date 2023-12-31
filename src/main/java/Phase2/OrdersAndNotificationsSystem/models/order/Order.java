package Phase2.OrdersAndNotificationsSystem.models.order;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


@Data
public abstract class Order {
    /**
     * The unique identifier for the order.
     */
    @Schema(example = "13")
    Integer id;

    /**
     * The account associated with the order.
     */
    Account account;

    /**
     * The status of the order (e.g., "Placed").
     */
    @Schema(example = "Placed")
    String status = "Placed";

    /**
     * The date and time when the order was created.
     */
    @Schema(example = "2021-05-01 12:00:00 PM +0200")
    LocalDateTime date;

    /**
     * The total price of the order.
     */
    @Schema(example = "100.0")
    Double price;
    ArrayList<Product> products = new ArrayList<>();
    Double shippingFee = 0.0;


    /**
     * Calculates the total fee for the order.
     *
     * @return The total fee for the order.
     * @throws GeneralException If an error occurs during the calculation.
     */
    public abstract Double calculateTotalFee() throws GeneralException;

    /**
     * Default constructor. Initializes the order's date to the current date and time.
     */
    Order() {
        this.date = LocalDateTime.now();
    }

    /**
     * Copy constructor. Creates a new order instance with the same attributes as the provided order.
     *
     * @param order The order to copy.
     */
    Order(Order order) {
        this.id = order.getId();
        this.account = new Account(order.getAccount());
        this.status = order.getStatus();
        this.date = order.getDate();
        this.price = order.getPrice();
        this.products = order.getProducts();
        this.shippingFee = order.getShippingFee();
    }
}