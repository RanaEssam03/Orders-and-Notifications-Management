package Phase2.OrdersAndNotificationsSystem.repositories.database;

import Phase2.OrdersAndNotificationsSystem.models.*;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static ArrayList<Notification> messagesQueue = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static Map<String, Integer> emailStatistics = new HashMap<>();
    public static Map<String, Integer> smsStatistics = new HashMap<>();

}
