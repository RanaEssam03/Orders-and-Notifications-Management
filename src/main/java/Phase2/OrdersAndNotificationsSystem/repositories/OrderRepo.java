package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Order;
import org.apache.el.stream.Optional;

public interface OrderRepo {
    Order addOrder(Order order);
    boolean removeOrder(Order order);
    Optional<Order> getOrder(int orderID);
}
