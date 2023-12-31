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
import java.util.List;
import java.util.Optional;

@Service
public class CompoundOrderServiceImpl extends OrderServices {



    int maxDifference = 5; // in minutes for cancellation

    public CompoundOrderServiceImpl(PlacementNotificationServices placementNotificationServices, ShipmentNotificationServices shipmentNotificationServices, CancellationNotificationServices cancellationNotificationServices) {
     super( placementNotificationServices, shipmentNotificationServices,  cancellationNotificationServices);

    }


    @Override
    public Order addOrder(Order order) throws GeneralException {

        enoughBalance(order);
        order.calculateTotalFee();
        for (Product product : (order).getProducts()) {
            productServices.reduceProductQuantity(product, 1);
        }
        order.setStatus("Placed");
        Order order1 = orderRepo.addOrder(order);
        accountServices.deduct(order1.getAccount(), order1.getPrice());
        placementNotificationServices.sendMessage(order1);
        return order1;
    }

    @Override
    public boolean cancelOrder(Order order) throws GeneralException {

        if (order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            ArrayList<Order> orders = ((CompoundOrder) order).getOrders();

            accountServices.refund(order.getAccount(), order.getPrice() + order.getShippingFee());

            confirmCancellationTime(order, "Placement or Confirming ");
            if (order.getStatus().equals("Cancelled"))
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled");

            for (Order o : orders) {
                if (o.getStatus().equals("Cancelled")) {
                    throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled");
                }
                accountServices.refund(o.getAccount(), o.getPrice() + o.getShippingFee());
                for (Product p : ((SimpleOrder) o).getProducts()) {
                    productServices.increaseProductQuantity(p, 1);
                }
                orderRepo.cancelOrder(o);
                cancellationNotificationServices.sendMessage(o);
            }

            cancellationNotificationServices.sendMessage(order);
            orderRepo.cancelOrder(order);

            orderRepo.cancelOrder(order);
        }

        return true;
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
            Double shippingFee = 30.0 / (orders.size() + 1);
            if (order.getAccount().getWalletBalance() < shippingFee)
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough balance for " + order.getAccount().getUsername() + " to confirm the order");
            order.setShippingFee(shippingFee);
            accountServices.deduct(order.getAccount(), shippingFee);
            if (order.getStatus().equals("Confirmed"))
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already confirmed");
            if (order.getStatus().equals("Cancelled"))
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled");

            for (Order currOrder : orders) {
                if (shippingFee > currOrder.getAccount().getWalletBalance())
                    throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough balance for " + currOrder.getAccount().getUsername() + " to confirm the order");
                else {
                    accountServices.deduct(currOrder.getAccount(), shippingFee);
                    currOrder.setStatus("Confirmed");
                    currOrder.setShippingFee(shippingFee);

                    shipmentNotificationServices.sendMessage(currOrder);
                }
            }
        }
        order.setStatus("Confirmed");
        return order;
    }




    @Override
    public void cancelShipment(Order order) throws GeneralException {

        confirmCancellationTime(order, "confirming");
        order.setStatus("Placed");
        double shipmentFee = order.getShippingFee();
        accountServices.refund(order.getAccount(), shipmentFee);
        order.setShippingFee(0.0);
        for (Order currentOrder : ((CompoundOrder) order).getOrders()) {
            accountServices.refund(currentOrder.getAccount(), shipmentFee);
            currentOrder.setShippingFee(0.0);
            currentOrder.setStatus("Placed");
        }

    }


}
