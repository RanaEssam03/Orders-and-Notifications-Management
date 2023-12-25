package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.repositories.InventoryRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InventoryImpl implements InventoryRepo {
  ArrayList<Category> categories = new ArrayList<>();
  static int id = 0;
  InventoryImpl() {
    Category category = new Category();
    category.setName("Electronics");
    category.setId(id++);
    categories.add(category);

    category = new Category();
    category.setName("Clothing");
    category.setId(id++);
    categories.add(category);

    category = new Category();
    category.setName("Furniture");
    category.setId(id++);
    categories.add(category);

    category = new Category();
    category.setName("Grocery");
    category.setId(id++);
    categories.add(category);

    category = new Category();
    category.setName("Books");
    category.setId(id++);
    categories.add(category);



  }


    @Override
    public ArrayList<Category> getCategories() {
        return null;
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

    @Override
    public ArrayList<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(String name) {
        return null;
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void removeCategory(Category category) {

    }
}