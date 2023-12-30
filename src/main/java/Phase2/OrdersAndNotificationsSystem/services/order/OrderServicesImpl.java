package Phase2.OrdersAndNotificationsSystem.services.order;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.order.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import Phase2.OrdersAndNotificationsSystem.services.notifications.CancellationNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.products.ProductServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.PlacementNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.ShipmentNotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderServicesImpl implements OrderServices {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    ProductServices productServices;

    @Autowired
    PlacementNotificationServices placementNotificationServices;

    @Autowired
    ShipmentNotificationServices shipmentNotificationServices;
    @Autowired
    CancellationNotificationServices cancellationNotificationServices;


    /**
     * Add the order to the database and reduce the quantity of the products in the order from the inventory
     * but no confirmation is done here or money is taken from the user's wallet
     * @param order the order to be placed
     * @return the order after adding it to the database
     * @throws GeneralException if the order is invalid or the user doesn't have enough balance to place the order
     */
    @Override
    public Order addOrder(Order order) throws GeneralException {

        if (order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            for (Product product :( (SimpleOrder)order).getProducts()) {
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
     * @param order the order to be confirmed
     * @throws GeneralException if the user doesn't have enough balance
     */
    @Override
    public boolean confirmSimpleOrder(Order order) throws GeneralException {
        if(order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else{
            if(order.getPrice() + 30 > order.getAccount().getWalletBalance())
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough balance");
            else {
                order.getAccount().setWalletBalance(order.getAccount().getWalletBalance() - order.getPrice() + 30);
                order.setPrice(order.getPrice() + 30);
                order.setStatus("Confirmed");
                shipmentNotificationServices.sendMessage(order);
            }
        }
        return true;
    }

    /**
     * Confirm the compound order by checking if the user has enough balance to place the order or not
     * @param order the order to be confirmed
     * @throws GeneralException if the user doesn't have enough balance
     */
    @Override
    public Order confirmCompoundOrder(Order order) throws GeneralException {
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

    /**
     * Check if the user has enough balance to place the order or not
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
