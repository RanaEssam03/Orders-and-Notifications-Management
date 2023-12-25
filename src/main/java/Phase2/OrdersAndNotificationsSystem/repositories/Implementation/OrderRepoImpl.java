package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;
import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;


@Repository
public class OrderRepoImpl implements OrderRepo {
    ArrayList<Order> orders = new ArrayList<>();
    static int id = 0;
    @Override
    public Order addOrder(Order order) {
        order.setId(id++);
        order.calculateTotalFee();
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
