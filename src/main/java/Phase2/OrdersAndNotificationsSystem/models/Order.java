package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public abstract class Order {
    Integer id;
    Account account;
    Address address;
    String status;
    String date;
    Double price;
    ArrayList<Product> products = new ArrayList<>();
    public abstract Double calculateTotalFee();
}
