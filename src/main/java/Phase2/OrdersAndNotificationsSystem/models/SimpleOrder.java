package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

import java.util.ArrayList;


@Data
public class SimpleOrder extends Order{

    @Override
    public Double calculateTotalFee() {
        Double totalPrice = 0.0;
        for (Product product : products){
            totalPrice += product.getPrice();
        }
        return (totalPrice + 30);
    }
}
