package Phase2.OrdersAndNotificationsSystem.models;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Product {
  /**
   * The serial number (ID) of the product.
   */
  @Schema(example = "12166237")
  Integer serialNumber; // ID

  /**
   * The name of the product.
   */
  @Schema(example = "Laptop")
  String name;

  /**
   * The vendor of the product.
   */
  @Schema(example = "HP")
  String vendor;

  /**
   * The price of the product.
   */
  @Schema(example = "20000.0")
  Double price;

  /**
   * The count of available units of the product.
   */
  @Schema(example = "5")
  Integer  productCount;

  /**
   * The list of categories associated with the product.
   */
  ArrayList<Category> categories = new ArrayList<>();

  /**
   * A static counter used to assign unique serial numbers (IDs) to products.
   */
  @Schema(example = "5")
  static int cnt = 0;

  /**
   * Default constructor. Initializes a product with a unique serial number.
   */
  public Product(){
      serialNumber = cnt++;
    }
  /**
   * Constructs a product with the specified parameters.
   *
   * @param name         The name of the product.
   * @param vendor       The vendor of the product.
   * @param price        The price of the product.
   * @param productCount The count of available units of the product.
   * @param categories   The list of categories associated with the product.
   */
  public Product(String name, String vendor, Double price, Integer productCount, ArrayList<Category> categories) {
    this.name = name;
    this.vendor = vendor;
    this.price = price;
    this.productCount = productCount;
    this.categories = categories;
    serialNumber = cnt++;
  }
}