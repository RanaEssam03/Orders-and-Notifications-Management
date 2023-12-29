package Phase2.OrdersAndNotificationsSystem.models;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Product {
  @Schema(example = "12166237")
  Integer serialNumber; // ID
  @Schema(example = "Laptop")
  String name;
  @Schema(example = "HP")
  String vendor;
  @Schema(example = "20000.0")
  Double price;
  @Schema(example = "5")
  Integer  productCount;
  ArrayList<Category> categories = new ArrayList<>();
  @Schema(example = "5")
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
