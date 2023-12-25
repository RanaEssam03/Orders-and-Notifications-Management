package Phase2.OrdersAndNotificationsSystem.services.Order;

import Phase2.OrdersAndNotificationsSystem.models.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.OrderRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompoundOrderService implements OrderServices {

    @Autowired
    OrderRepoImpl orderRepo;

    @Override
    public Order addOrder(Order order) throws GeneralException {
        CompoundOrder compoundOrder = (CompoundOrder) order;
        if(compoundOrder == null){
            throw new GeneralException("400", "Invalid order");
        }
        else{
            return orderRepo.addOrder(compoundOrder);
        }
    }

    @Override
    public boolean removeOrder(Order order) throws GeneralException {
        return false;
    }

    @Override
    public Optional<Order> getOrder(int orderID) throws GeneralException {
        return Optional.empty();
    }
}
