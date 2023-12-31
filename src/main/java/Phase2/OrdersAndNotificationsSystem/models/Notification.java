package Phase2.OrdersAndNotificationsSystem.models;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Notification {
    /**
     * The content of the notification message.
     */
    String content;

    /**
     * The unique identifier (ID) for the notification.
     */
    int id;

    /**
     * The order associated with the notification.
     */
    Order order;

    /**
     * The type of message channel through which the notification is sent.
     */
    String channelType = "";

    /**
     * A static counter used to assign unique IDs to notifications.
     */
    @JsonIgnore
    static int count = 0;

    /**
     * Constructs a Notification with the specified content and associated order.
     * The ID is assigned a unique value.
     *
     * @param msg The content of the notification message.
     * @param order   The order associated with the notification.
     */
    public Notification(String msg, Order order) {
        this.content = msg;
        this.order = order;
        id = ++count;
    }
}
