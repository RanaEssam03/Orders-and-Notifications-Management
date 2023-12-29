package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface InventoryRepo {

    ArrayList<Category> getCategories();
    Category getCategory(String name);

    Category getCategory(int id);

    ArrayList<Product> getProducts();

    Product getProduct(String name);
    Product getProduct(int id);

    void addCategory(Category category);
    void addProduct(Product product);
    void updateProduct(Product product);
    void removeCategory(Category category);



}
