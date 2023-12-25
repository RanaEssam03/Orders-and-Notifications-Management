package Phase2.OrdersAndNotificationsSystem.models;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    ArrayList<Product> products = new ArrayList<>();


//    @Override
//    Double calculateShipmentFee() {
//
//    }

    @Override
    public Double calculateTotalFee() {
        Double totalPrice = 0.0;
        for (Product product : products){
            totalPrice += product.getPrice();
        }
        setPrice(totalPrice + (totalPrice + 30));
        return totalPrice + (totalPrice + 30);
    }
}
