package Phase2.OrdersAndNotificationsSystem.services.Order;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

import java.util.Optional;

public interface OrderServices {


    Order addOrder(Order order) throws GeneralException;

    boolean removeOrder(Order order) throws GeneralException;
    Optional<Order> getOrder(int orderID) throws GeneralException;

    boolean confirmSimpleOrder(Order order) throws GeneralException;

    Order confirmCompoundOrder(Order order) throws GeneralException;

}
