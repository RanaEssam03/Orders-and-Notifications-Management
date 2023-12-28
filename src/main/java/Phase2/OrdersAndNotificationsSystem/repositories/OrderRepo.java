package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

import java.util.Optional;

public interface OrderRepo {
    Order addOrder(Order order) throws GeneralException;
    boolean removeOrder(Order order);
    Optional<Order> getOrder(int orderID);
}
