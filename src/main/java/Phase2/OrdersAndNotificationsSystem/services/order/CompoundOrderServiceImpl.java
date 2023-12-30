package Phase2.OrdersAndNotificationsSystem.services.order;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import Phase2.OrdersAndNotificationsSystem.services.notifications.CancellationNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.NotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.PlacementNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.ShipmentNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.products.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompoundOrderServiceImpl implements OrderServices {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    ProductServices productServices;

    NotificationServices placementNotificationServices;


    NotificationServices shipmentNotificationServices;

    NotificationServices cancellationNotificationServices;

    public CompoundOrderServiceImpl(PlacementNotificationServices placementNotificationServices, ShipmentNotificationServices shipmentNotificationServices, CancellationNotificationServices cancellationNotificationServices) {
        this.placementNotificationServices = placementNotificationServices;
        this.shipmentNotificationServices = shipmentNotificationServices;
        this.cancellationNotificationServices = cancellationNotificationServices;
    }



    @Override
    public boolean cancelOrder(Order order) throws GeneralException {

        // TODO
        return false;
    }

    @Override
    public Optional<Order> getOrder(int orderID) throws GeneralException {
        return Optional.empty();
    }

    @Override
    public Order confirmOrder(Order order) throws GeneralException {
        if(order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else{
            ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
            Integer shippingFee = 30/orders.size();
            for (Order currOrder : orders){
                if(currOrder.getPrice() + shippingFee > currOrder.getAccount().getWalletBalance())
                    throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough balance for " + currOrder.getAccount().getUsername() + " to confirm the order");
                else {
                    currOrder.getAccount().setWalletBalance(currOrder.getAccount().getWalletBalance() - currOrder.getPrice() + shippingFee);
                    currOrder.setStatus("Confirmed");
                    currOrder.setPrice(currOrder.getPrice() + shippingFee);

                    for (Order o: orders) {
                        shipmentNotificationServices.sendMessage(o);
                    }
                }

            }
        }
        return orderRepo.addOrder(order);
    }


}
