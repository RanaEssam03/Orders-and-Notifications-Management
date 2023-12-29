package Phase2.OrdersAndNotificationsSystem.models;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.services.notifications.MessageChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.NotificationServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlacementNotificationServices extends NotificationServices {
    static private Integer id;
    Map<String, String> content = new HashMap<>();
    void initializeMap(){
        content.put("English", "Dear %s, your order of %s is confirmed. thanks for using our online store :)");
        content.put("German", "");
        content.put("French", "");
    }
    public PlacementNotificationServices(MessageChannel messageChannel) {
        super(messageChannel);
        initializeMap();
    }
    @Override
    protected String createMessage(Order order) {

    }
}
