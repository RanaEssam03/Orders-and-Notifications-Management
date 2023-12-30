package Phase2.OrdersAndNotificationsSystem.services.order;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.order.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import Phase2.OrdersAndNotificationsSystem.services.account_services.AccountServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.CancellationNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.NotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.PlacementNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.ShipmentNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.products.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompoundOrderServiceImpl implements OrderServices {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    AccountServices accountServices;

    @Autowired
    ProductServices productServices;

    NotificationServices placementNotificationServices;


    NotificationServices shipmentNotificationServices;

    NotificationServices cancellationNotificationServices;

    int maxDifference = 5; // in minutes

    public CompoundOrderServiceImpl(PlacementNotificationServices placementNotificationServices, ShipmentNotificationServices shipmentNotificationServices, CancellationNotificationServices cancellationNotificationServices) {
        this.placementNotificationServices = placementNotificationServices;
        this.shipmentNotificationServices = shipmentNotificationServices;
        this.cancellationNotificationServices = cancellationNotificationServices;
    }


    @Override
    public boolean cancelOrder(Order order) throws GeneralException {

        if (order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            ArrayList<Order> orders = ((CompoundOrder) order).getOrders();

            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(order.getDate(), now);
            if (duration.toMinutes() > maxDifference)
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Order can't be cancelled after 5 minute of confirming it");
            if(order.getStatus().equals("Cancelled"))
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled");

            for (Order o : orders) {
                if (o.getStatus().equals("Cancelled")) {
                    throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled");
                }

                accountServices.refund(o.getAccount(), o.getPrice());
                for (Product p : ((SimpleOrder) o).getProducts()) {
                    productServices.increaseProductQuantity(p, 1);
                }
                orderRepo.cancelOrder(o);
                cancellationNotificationServices.sendMessage(o);
            }

            cancellationNotificationServices.sendMessage(order);
            orderRepo.cancelOrder(order);
        }

        return false;
    }

    @Override
    public Optional<Order> getOrder(int orderID) throws GeneralException {
        return orderRepo.getOrder(orderID);
    }

    @Override
    public Order confirmOrder(Order order) throws GeneralException {
        if (order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
            Integer shippingFee = 30 / orders.size();
            for (Order currOrder : orders) {
                if (currOrder.getPrice() + shippingFee > currOrder.getAccount().getWalletBalance())
                    throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough balance for " + currOrder.getAccount().getUsername() + " to confirm the order");
                else {
                    currOrder.getAccount().setWalletBalance(currOrder.getAccount().getWalletBalance() - (currOrder.getPrice() + shippingFee));
                    currOrder.setStatus("Confirmed");
                    currOrder.setPrice(currOrder.getPrice() + shippingFee);

                    for (Order o : orders) {
                        shipmentNotificationServices.sendMessage(o);
                    }
                }

            }
        }
        return orderRepo.addOrder(order);
    }


}
