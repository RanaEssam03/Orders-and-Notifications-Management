package Phase2.OrdersAndNotificationsSystem.models.order;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;


@Data
public class CompoundOrder extends Order {
    /**
     * The list of individual orders that make up the compound order.
     */
    ArrayList<Order> orders  = new ArrayList<>();

    /**
     * Constructs a compound order with the same attributes as the provided order.
     * Additionally, it creates copies of individual orders within the compound order.
     *
     * @param order The order to copy and include in the compound order.
     */
    public CompoundOrder(Order order) {
        super(order);
        for (Order o : ((CompoundOrder) order).getOrders()){
            this.orders.add(new SimpleOrder(o));
        }
    }

    /**
     * Default constructor. Initializes the compound order with an empty list of orders.
     */
    public CompoundOrder() {
        super();
    }

    /**
     * Calculates the total fee for the compound order, including the sum of individual order prices
     * and an additional fixed fee per order.
     *
     * @return The total fee for the compound order.
     * @throws GeneralException If an error occurs during the calculation.
     */
    public Double calculateTotalFee() throws GeneralException {
        double totalPrice = 0.0;

        // Add a fixed fee per order
        totalPrice += ((double)30/(orders.size()));
        for (Order order : orders){
            totalPrice += order.getPrice();
        }

        // Set the total price for the compound order
        this.setPrice(totalPrice);

        return totalPrice;
    }
}