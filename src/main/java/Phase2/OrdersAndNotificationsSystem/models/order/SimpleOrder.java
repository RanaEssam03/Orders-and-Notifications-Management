package Phase2.OrdersAndNotificationsSystem.models.order;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import lombok.Data;


@Data
public class SimpleOrder extends Order {

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
