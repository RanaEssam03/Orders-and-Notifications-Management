package Phase2.OrdersAndNotificationsSystem.models;

import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.ProductRepoImpl;
import Phase2.OrdersAndNotificationsSystem.repositories.ProductRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


@Data
public class CompoundOrder extends Order{
    ArrayList<SimpleOrder> orders  = new ArrayList<>();

    @Override
    public Double calculateTotalFee() {
        double totalPrice = 0.0;

        for (Product product : products){
            totalPrice += product.getPrice();
        }

        for (Order order : orders){
            double price = (order.calculateTotalFee()) - (30) + (double)30/orders.size();
            totalPrice += price;
        }
        return totalPrice;
    }
}
