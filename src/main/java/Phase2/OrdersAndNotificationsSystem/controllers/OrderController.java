package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.services.Order.OrderServices;
import Phase2.OrdersAndNotificationsSystem.services.Order.SimpleOrderServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    SimpleOrderServicesImpl orderServices;
    @PostMapping("/make-order")
    public Order makeOrder(@RequestBody SimpleOrder order) throws GeneralException {
        return orderServices.addOrder(order);
    }

    @PostMapping("/make-compound-order")
    public Order makeCompoundOrder(@RequestBody CompoundOrder order) throws GeneralException {
        return orderServices.addOrder(order);
    }
}
