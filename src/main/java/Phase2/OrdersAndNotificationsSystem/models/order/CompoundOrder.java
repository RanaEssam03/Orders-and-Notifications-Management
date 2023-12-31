package Phase2.OrdersAndNotificationsSystem.models.order;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;


@Data
public class CompoundOrder extends Order {
    ArrayList<Order> orders  = new ArrayList<>();

    public CompoundOrder(Order order) {
        super(order);
        for (Order o : ((CompoundOrder) order).getOrders()){
            this.orders.add(new SimpleOrder(o));
        }
    }

    public CompoundOrder() {
        super();
    }

    public Double calculateTotalFee() throws GeneralException {
        double totalPrice = 0.0;
        for(Product product : products){
            totalPrice += product.getPrice();
        }
        price = totalPrice;
        return totalPrice;
    }
}
