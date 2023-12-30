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
    @Schema(example = "13")
    Integer id;
    Account account;
    @Schema(example = "Placed")
    String status = "Placed";
    @Schema(example = "2021-05-01 12:00:00 PM +0200")
    LocalDateTime date;
    @Schema(example = "100.0")
    Double price;


    public abstract Double calculateTotalFee() throws GeneralException;

    Order() {
       this.date = LocalDateTime.now();
    }
}
