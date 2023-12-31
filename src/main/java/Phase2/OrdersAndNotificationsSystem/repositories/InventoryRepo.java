package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface InventoryRepo {


    Category getCategory(String name);

    Category getCategory(int id);





}
