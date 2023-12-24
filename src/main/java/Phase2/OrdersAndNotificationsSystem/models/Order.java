package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Order {
    String id;
    ArrayList<Product> products;
    Account account;
    Address address;
    String status;
    Double totalPrice;
    String date;
}
