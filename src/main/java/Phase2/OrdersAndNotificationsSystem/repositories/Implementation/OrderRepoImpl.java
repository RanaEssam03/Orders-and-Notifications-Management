package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;

import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import org.apache.el.stream.Optional;

import java.util.ArrayList;

public class OrderRepoImpl implements OrderRepo {
    ArrayList<Order> orders = new ArrayList<>();
    @Override
    public Order addOrder(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public boolean removeOrder(Order order) {
        return orders.remove(order);
    }

    @Override
    public Optional getOrder(int orderID) {
        for (Order order : orders){
            if (order.getId() == orderID) return order;
        }
        return null;
    }
}
