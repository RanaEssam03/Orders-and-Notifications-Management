package Phase2.OrdersAndNotificationsSystem.repositories.implementation;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.repositories.InventoryRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.categories;

@Repository
public class InventoryImpl implements InventoryRepo {

  InventoryImpl() {



  }




    @Override
    public Category getCategory(String name) {
      for (Category category : categories) {
        if (category.getName().equals(name)) {
          return category;
        }
      }
        return null;
    }

    @Override
    public Category getCategory(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
            return category;
            }
        }
        return null;

    }


}
