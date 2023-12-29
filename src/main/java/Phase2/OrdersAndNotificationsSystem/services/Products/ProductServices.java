package Phase2.OrdersAndNotificationsSystem.services.Products;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;

import java.util.ArrayList;

public interface ProductServices {
    ArrayList<Product> getAllProducts() throws GeneralException;
    ArrayList<Product> getProductsByCategory(Integer id) throws GeneralException;
    ArrayList<Product> getProductsByID(ArrayList<Integer>IDs) throws GeneralException;

    boolean reduceProductQuantity(Product product, Integer quantity) throws GeneralException;

//   public boolean validProductCount(Order order) throws GeneralException;


}
