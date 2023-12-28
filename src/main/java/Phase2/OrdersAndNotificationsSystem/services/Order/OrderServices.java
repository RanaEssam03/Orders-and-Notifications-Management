package Phase2.OrdersAndNotificationsSystem.services.Order;

import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.OrderRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface OrderServices {


    Order addOrder(Order order, String username) throws GeneralException;

    boolean removeOrder(Order order) throws GeneralException;
    Optional<Order> getOrder(int orderID) throws GeneralException;

}
