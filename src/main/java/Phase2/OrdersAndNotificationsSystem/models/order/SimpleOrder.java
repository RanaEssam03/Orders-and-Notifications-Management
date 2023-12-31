package Phase2.OrdersAndNotificationsSystem.models.order;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import lombok.Data;

import java.util.ArrayList;


@Data
public class SimpleOrder extends Order {

    /**
     * Constructs a simple order with the same attributes as the provided order.
     * Additionally, it creates copies of products within the simple order.
     *
     * @param order The order to copy and include in the simple order.
     */
    public SimpleOrder(Order order) {
        super(order);

    }

    /**
     * Default constructor. Initializes the simple order with an empty list of products.
     */
    public SimpleOrder() {
        super();
    }

    /**
     * Calculates the total fee for the simple order, including the sum of prices of individual products.
     *
     * @return The total fee for the simple order.
     * @throws GeneralException If an error occurs during the calculation.
     */
    @Override
    public Double calculateTotalFee() throws GeneralException {
        Double totalPrice = 0.0;

        // Add the price of each individual product
        for (Product product : products){
            totalPrice += product.getPrice();
        }

        // Set the total price for the simple order
        this.setPrice(totalPrice);
        return (totalPrice);
    }
}