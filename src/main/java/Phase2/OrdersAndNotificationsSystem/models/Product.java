package Phase2.OrdersAndNotificationsSystem.models;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Product {
    String serialNumber; // ID
    String name;
    String vendor;
    Double price;
    Integer  productCount;
    ArrayList<Category> categories;
}
