package Phase2.OrdersAndNotificationsSystem.models;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;


@Data
public class SimpleOrder extends Order{

    @Override
    public Double calculateTotalFee() throws GeneralException {
        Double totalPrice = 0.0;
        for (Product product : products){
            totalPrice += product.getPrice();
        }
        this.setPrice(totalPrice);
        return (totalPrice);

    }
}
