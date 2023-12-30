package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import lombok.Data;

@Data
public class NotificationDTO {
    private String message_channel_type;
    private String content;
    private Integer orderID;
    private String username;

    public NotificationDTO(String messageChannel, String content, Integer orderID, String username) {
        this.message_channel_type = messageChannel;
        this.content = content;
        this.orderID = orderID;
        this.username = username;
    }

    public NotificationDTO(Notification notification) {
        this.message_channel_type = notification.getChannelType();
        this.content = notification.getContent();
        this.orderID = notification.getOrder().getId();
        this.username = notification.getOrder().getAccount().getUsername();
    }

}
