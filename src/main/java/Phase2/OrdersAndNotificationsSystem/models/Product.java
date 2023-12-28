package Phase2.OrdersAndNotificationsSystem.models;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Product {
  Integer serialNumber; // ID
    String name;
    String vendor;
    Double price;
    Integer  productCount;
    ArrayList<Category> categories = new ArrayList<>();
    static int cnt = 0;

    public Product(){
      serialNumber = cnt++;
    }

  public Product(String name, String vendor, Double price, Integer productCount, ArrayList<Category> categories) {
    this.name = name;
    this.vendor = vendor;
    this.price = price;
    this.productCount = productCount;
    this.categories = categories;
    serialNumber = cnt++;
  }
}
