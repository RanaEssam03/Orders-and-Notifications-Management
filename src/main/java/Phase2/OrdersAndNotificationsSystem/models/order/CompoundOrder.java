package Phase2.OrdersAndNotificationsSystem.models.order;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;


@Data
public class CompoundOrder extends Order {
    ArrayList<Order> orders  = new ArrayList<>();

    @Override // TODO
    public Double calculateTotalFee() throws GeneralException {
        double totalPrice = 0.0;

//        for (Product product : products){
//            totalPrice += product.getPrice();
//        }
        totalPrice += ((double)30/(orders.size()+1));
        this.setPrice(totalPrice);
        if(totalPrice > this.getAccount().getWalletBalance()){
            String message = "Not enough balance for " + this.getAccount().getUsername();
            throw new GeneralException(HttpStatus.BAD_REQUEST, message);
        }
        for (Order order : orders){
            double price = (order.calculateTotalFee()) + ((double)30/(orders.size()+1));
            order.setPrice(price);
            if(price > order.getAccount().getWalletBalance()){
                String message = "Not enough balance for " + order.getAccount().getUsername();
                throw new GeneralException(HttpStatus.BAD_REQUEST, message);
            }
            totalPrice += price;
        }
        return totalPrice;
    }
}
