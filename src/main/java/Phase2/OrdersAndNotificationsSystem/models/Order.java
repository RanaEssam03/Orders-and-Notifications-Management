package Phase2.OrdersAndNotificationsSystem.models;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Data
public abstract class Order {
    @Schema(example = "13")
    Integer id;
    Account account;

    @Schema(example = "Pending")
    String status;
    @Schema(example = "2021/05/05")
    String date;
    @Schema(example = "100.0")
    Double price;

    ArrayList<Product> products = new ArrayList<>();

    public abstract Double calculateTotalFee() throws GeneralException;

    Order() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        this.date = str;
    }
}
