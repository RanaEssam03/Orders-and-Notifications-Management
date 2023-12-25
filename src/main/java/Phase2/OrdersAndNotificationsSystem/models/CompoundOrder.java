package Phase2.OrdersAndNotificationsSystem.models;

import java.util.ArrayList;

public class CompoundOrder extends Order{
    ArrayList<Order> orders = new ArrayList<>();

    @Override
    public Double calculateTotalFee() {
        double totalPrice = 0.0;
        for (Order order : orders){
            double price = (order.calculateTotalFee()) - (30) + (double)30/orders.size();
            totalPrice += price;
        }
        return totalPrice;
    }
}
