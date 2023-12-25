package Phase2.OrdersAndNotificationsSystem.services.Order;

import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.OrderRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;



@Service
public class SimpleOrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepoImpl orderRepo;



    @Override
    public Order addOrder(Order order) throws GeneralException {
        SimpleOrder simpleOrder = (SimpleOrder) order;
        if(order == null){
            throw new GeneralException("400", "Invalid order");
        }
        else{
            return orderRepo.addOrder(simpleOrder);
        }

    }


    @Override
    public boolean removeOrder(Order order) throws GeneralException {
        if(order == null){
            throw new GeneralException("400", "Invalid order");
        }
        else
            return orderRepo.removeOrder(order);
    }

    @Override
    public Optional<Order> getOrder(int orderID) throws GeneralException {
        Optional<Order> order = orderRepo.getOrder(orderID);
        if (order.isPresent())
            return order;
        else
            throw new GeneralException("401", "Invalid order id");
    }
}
