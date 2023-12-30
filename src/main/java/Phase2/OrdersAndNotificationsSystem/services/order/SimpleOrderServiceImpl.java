package Phase2.OrdersAndNotificationsSystem.services.order;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
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
import java.util.Optional;

@Service
public class SimpleOrderServiceImpl implements OrderServices {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    AccountServices accountServices;

    @Autowired
    ProductServices productServices;

    int maxDifference = 5; // in minutes

    NotificationServices placementNotificationServices;


    NotificationServices shipmentNotificationServices;

    NotificationServices cancellationNotificationServices;

    public SimpleOrderServiceImpl(PlacementNotificationServices placementNotificationServices, ShipmentNotificationServices shipmentNotificationServices, CancellationNotificationServices cancellationNotificationServices) {
        this.placementNotificationServices = placementNotificationServices;
        this.shipmentNotificationServices = shipmentNotificationServices;
        this.cancellationNotificationServices = cancellationNotificationServices;
    }

    /**
     * Add the order to the database and reduce the quantity of the products in the order from the inventory
     * but no confirmation is done here or money is taken from the user's wallet
     *
     * @param order the order to be placed
     * @return the order after adding it to the database
     * @throws GeneralException if the order is invalid or the user doesn't have enough balance to place the order
     */
    @Override
    public Order addOrder(Order order) throws GeneralException {

        if (order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            for (Product product : ((SimpleOrder) order).getProducts()) {
                productServices.reduceProductQuantity(product, 1);
            }

            enoughBalance(order);
        }
        order.setStatus("Pending");
        Order order1 = orderRepo.addOrder(order);
        placementNotificationServices.sendMessage(order1);
        return order1;
    }

    @Override
    public boolean cancelOrder(Order order) throws GeneralException {

        if (order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            if (order.getStatus().equals("Cancelled")) {
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled");
            }

            LocalDateTime now = LocalDateTime.now();

            Duration duration = Duration.between(order.getDate(), now);


            if (duration.toMinutes() > maxDifference)
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Can't cancel order after 5 minutes");

            order.setStatus("Cancelled");
            accountServices.refund(order.getAccount(), order.getPrice());
            for (Product product : ((SimpleOrder) order).getProducts()) {
                productServices.increaseProductQuantity(product, 1);
            }

            cancellationNotificationServices.sendMessage(order);

            return orderRepo.cancelOrder(order);
        }
    }

    @Override
    public Optional<Order> getOrder(int orderID) throws GeneralException {
        Optional<Order> order = orderRepo.getOrder(orderID);
        if (order.isPresent())
            return order;
        else
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
    }

    /**
     * Confirm the simple order by checking if the user has enough balance to place the order or not
     *
     * @param order the order to be confirmed
     * @throws GeneralException if the user doesn't have enough balance
     */
    @Override
    public Order confirmOrder(Order order) throws GeneralException {
        if (order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            if (order.getPrice() + 30 > order.getAccount().getWalletBalance())
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough balance");
            else {
                order.getAccount().setWalletBalance(order.getAccount().getWalletBalance() -( order.getPrice() + 30));
                order.setPrice(order.getPrice() + 30);
                order.setStatus("Confirmed");
                shipmentNotificationServices.sendMessage(order);
            }
        }
        return order;
    }


    /**
     * Check if the user has enough balance to place the order or not
     *
     * @param order the order to be placed
     * @throws GeneralException if the user doesn't have enough balance
     */
    void enoughBalance(Order order) throws GeneralException {
        double totalFee = order.calculateTotalFee();
        if (order instanceof SimpleOrder) {
            if (totalFee > order.getAccount().getWalletBalance()) {
                String message = "Not enough balance for " + order.getAccount().getUsername();
                throw new GeneralException(HttpStatus.BAD_REQUEST, message);
            }
            order.setPrice(totalFee);
        }
    }
}
