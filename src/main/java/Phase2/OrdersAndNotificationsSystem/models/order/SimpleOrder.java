package Phase2.OrdersAndNotificationsSystem.models.order;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import lombok.Data;

import java.util.ArrayList;


@Data
public class SimpleOrder extends Order {

    public SimpleOrder(Order order) {
        super(order);

    }

   public SimpleOrder() {
        super();
    }


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
