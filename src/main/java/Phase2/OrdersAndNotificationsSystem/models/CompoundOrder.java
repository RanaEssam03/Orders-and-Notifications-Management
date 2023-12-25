package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CompoundOrder extends Order{
    ArrayList<SimpleOrder> orders ;

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
