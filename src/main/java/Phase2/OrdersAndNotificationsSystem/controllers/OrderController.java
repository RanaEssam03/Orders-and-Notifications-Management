package Phase2.OrdersAndNotificationsSystem.controllers;
import Phase2.OrdersAndNotificationsSystem.models.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.services.Order.CompoundOrderService;
import Phase2.OrdersAndNotificationsSystem.services.Order.SimpleOrderServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    SimpleOrderServicesImpl orderServices;

    @Autowired
    CompoundOrderService compoundOrderService;

    @PostMapping("/make-order")
    public Order makeOrder(@RequestBody SimpleOrder order) throws GeneralException {
        return orderServices.addOrder(order);
    }

    @PostMapping("/make-compound-order")
    public CompoundOrder makeCompoundOrder(@RequestBody CompoundOrder order) throws GeneralException {
        return (CompoundOrder) compoundOrderService.addOrder(order);
    }

    @GetMapping("/get-order/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Integer id) throws GeneralException {
        return orderServices.getOrder(id);
    }

}
