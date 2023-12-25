package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import java.util.ArrayList;

public class SimpleOrder {
    Integer accountID;
    Integer productID;

    ArrayList<Integer> orders = new ArrayList<>();
}
