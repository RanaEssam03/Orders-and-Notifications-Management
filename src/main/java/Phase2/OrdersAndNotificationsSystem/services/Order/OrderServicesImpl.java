package Phase2.OrdersAndNotificationsSystem.services.Order;
import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.OrderRepoImpl;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import Phase2.OrdersAndNotificationsSystem.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    AccountRepo accountRepo;

     
    @Override
    public Order addOrder(Order order) throws GeneralException {

        if(order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {

            // TODO make sure that the total is compatible with the available balance for each user
//            double total = order.getPrice();
//            if(total)
            return orderRepo.addOrder(order);
        }
    }

    @Override
    public boolean removeOrder(Order order) throws GeneralException {
        if(order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else
            return orderRepo.removeOrder(order);
    }

    @Override
    public Optional<Order> getOrder(int orderID) throws GeneralException {
        Optional<Order> order = orderRepo.getOrder(orderID);
        if (order.isPresent())
            return order;
        else
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
    }
}
