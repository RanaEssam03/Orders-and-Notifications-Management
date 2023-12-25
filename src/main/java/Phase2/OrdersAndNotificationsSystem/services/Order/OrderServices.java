package Phase2.OrdersAndNotificationsSystem.services.Order;

import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

import java.util.Optional;

public interface OrderServices {

    Order addOrder(Order order) throws GeneralException;

    boolean removeOrder(Order order) throws GeneralException;
    Optional<Order> getOrder(int orderID) throws GeneralException;

}
