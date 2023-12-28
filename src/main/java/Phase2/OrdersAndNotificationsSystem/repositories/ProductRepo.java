package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProductRepo {

    Optional<Product> getProduct(Integer serialNumber);

    void addProduct(Product product);
    boolean updateProduct(Product product);
    void removeProduct(Product product);
    Optional<ArrayList<Product>> getAllProducts();

    ArrayList<Product> getProductsByCategory(Category category);
}
