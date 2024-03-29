package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

import java.util.Optional;

public interface OrderRepo {
    Order addOrder(Order order) throws GeneralException;
    boolean cancelOrder(Order order);
    Optional<Order> getOrder(int orderID);
}
