package Phase2.OrdersAndNotificationsSystem.services.order;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.order.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import Phase2.OrdersAndNotificationsSystem.services.account_services.AccountServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.NotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.products.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class OrderServices {
    int maxDifference = 5; // in minutes

    @Autowired
    AccountServices accountServices;

    @Autowired
    ProductServices productServices;

    @Autowired
    OrderRepo orderRepo;

    NotificationServices placementNotificationServices;


    NotificationServices shipmentNotificationServices;

    NotificationServices cancellationNotificationServices;

    public OrderServices(NotificationServices placementNotificationServices, NotificationServices shipmentNotificationServices, NotificationServices cancellationNotificationServices) {
        this.placementNotificationServices = placementNotificationServices;
        this.shipmentNotificationServices = shipmentNotificationServices;
        this.cancellationNotificationServices = cancellationNotificationServices;
    }

    public abstract Order addOrder(Order order) throws GeneralException;

    public abstract boolean cancelOrder(Order order) throws GeneralException;

    public abstract Optional<Order> getOrder(int orderID) throws GeneralException;

    public abstract Order confirmOrder(Order order) throws GeneralException;



    /**
     * Check if the user has enough balance to place the order or not
     *
     * @param order the order to be placed
     * @throws GeneralException if the user doesn't have enough balance
     */
    protected void enoughBalance(Order order) throws GeneralException {
        double totalFee = order.calculateTotalFee();
        if (order instanceof SimpleOrder) {
            if (totalFee > order.getAccount().getWalletBalance()) {
                String message = "Not enough balance for " + order.getAccount().getUsername();
                throw new GeneralException(HttpStatus.BAD_REQUEST, message);
            }
            order.setPrice(totalFee);
        }
    }

    abstract public void cancelShipment(Order order) throws GeneralException;

    public boolean confirmCancellationTime(Order order, String status) throws GeneralException {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(order.getDate(), now);

        if (duration.toMinutes() > maxDifference)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Order can't be cancelled after 5 minute of "+ status+"it");

        return true;
    }


}
