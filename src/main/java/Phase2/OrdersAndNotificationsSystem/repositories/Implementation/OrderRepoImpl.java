package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;

import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.ArrayList;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.orders;

@Repository
public class OrderRepoImpl implements OrderRepo {
    private static int ids = 0;

    @Override
    public Order addOrder(Order order) throws GeneralException {
        order.setId(++ids);
        orders.add(order);
        return order;
    }

    @Override
    public boolean removeOrder(Order order) {
        return orders.remove(order);
    }

    @Override
    public Optional<Order> getOrder(int orderID) {
        for (Order order : orders){
            if (order.getId() == orderID) return Optional.of(order);
        }
        return Optional.empty();
    }
}
