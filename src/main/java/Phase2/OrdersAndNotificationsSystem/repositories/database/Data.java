package Phase2.OrdersAndNotificationsSystem.repositories.database;

import Phase2.OrdersAndNotificationsSystem.models.*;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static ArrayList<Notification> messagesQueue = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<Product>();

}
