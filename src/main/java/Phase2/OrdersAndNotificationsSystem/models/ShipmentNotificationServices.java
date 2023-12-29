package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class ShipmentNotificationServices {
    static private Integer id;
    Map<String, String> content = new HashMap<>();
    String accountUsername;
    ArrayList<String> productNames = new ArrayList<>();
    void initializeMap(){
        content.put("English", "Dear %s, your shipment of %s is confirmed. thanks for using our online store :)");
        content.put("German", "");
        content.put("French", "");
    }
    public ShipmentNotificationServices(String contact) {
        initializeMap();
        this.accountUsername = contact;
    }
}
