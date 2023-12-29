package Phase2.OrdersAndNotificationsSystem.services.Order;
import Phase2.OrdersAndNotificationsSystem.models.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import Phase2.OrdersAndNotificationsSystem.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    AccountRepo accountRepo;


     
    @Override
    public Order addOrder(Order order) throws GeneralException {

        if(order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else {
            Map<Product, Integer> productCount = new HashMap<>();
            ArrayList<Product > products;
            if(order instanceof CompoundOrder){
                ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
                for(Order currOrder : orders){
                    products = currOrder.getProducts();
                    for (Product product : products) {
                        productCount.put(product, 0);
                    }
                    for(Product p :products){
                        productCount.put(p ,productCount.get(p) + 1);
                        if(productCount.get(p) > p.getProductCount()){
                            throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough products in stock");
                        }
                    }
                }
            }
            products = order.getProducts();
            for (Product product : products) {
                 productCount.put(product, 0);
            }
            for(Product p :products){
                productCount.put(p ,productCount.get(p) + 1);
                if(productCount.get(p) > p.getProductCount()){
                    throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough products in stock");
                }
            }

            double totalFee = order.calculateTotalFee();
            if(order instanceof SimpleOrder){
                if(totalFee + 30 > order.getAccount().getWalletBalance()){
                    String message = "Not enough balance for " + order.getAccount().getUsername();
                    throw new GeneralException(HttpStatus.BAD_REQUEST, message);
                }
                order.setPrice(totalFee + 30);

            }
            order.getAccount().setWalletBalance(order.getAccount().getWalletBalance() - order.getPrice());
            if(order instanceof CompoundOrder){
                checkValidCompoundOrder((CompoundOrder) order);
                ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
                for(Order currOrder : orders){
                    currOrder.getAccount().setWalletBalance(currOrder.getAccount().getWalletBalance() - currOrder.getPrice());
                }
            }


            return orderRepo.addOrder(order);
        }
    }

    @Override
    public boolean removeOrder(Order order) throws GeneralException {
        if(order == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
        else
            return orderRepo.removeOrder(order);
    }

    @Override
    public Optional<Order> getOrder(int orderID) throws GeneralException {
        Optional<Order> order = orderRepo.getOrder(orderID);
        if (order.isPresent())
            return order;
        else
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
    }

    Boolean checkValidCompoundOrder(CompoundOrder compoundOrder) throws GeneralException {
        ArrayList<Order> orders = compoundOrder.getOrders();
        for(Order order : orders){
            if(order.getAccount().getUsername() == compoundOrder.getAccount().getUsername()){
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid order");
            }
        }
        String temp = compoundOrder.getAccount().getAddress().getCity();
        for(Order order : orders){
            if(order.getAccount().getAddress().getCity() != temp){
                throw new GeneralException(HttpStatus.NOT_ACCEPTABLE, "Not all orders are in the same city");
            }
        }
        return true;
    }
}
