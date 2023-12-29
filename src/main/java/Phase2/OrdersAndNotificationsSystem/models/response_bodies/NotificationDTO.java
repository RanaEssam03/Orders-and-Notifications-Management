package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.services.notifications.SMSChannel;
import lombok.Data;

@Data
public class NotificationDTO {
    private String messageChannel;
    private String content;
    private Integer orderID;
    private String username;

    public NotificationDTO(String messageChannel, String content, Integer orderID, String username) {
        this.messageChannel = messageChannel;
        this.content = content;
        this.orderID = orderID;
        this.username = username;
    }

    public NotificationDTO(Notification notification) {
        if (notification.getMessageChannel() instanceof SMSChannel)
            this.messageChannel = "SMS";
        else
            this.messageChannel = "Email";

        this.content = notification.getContent();
        this.orderID = notification.getOrder().getId();
        this.username = notification.getOrder().getAccount().getUsername();
    }
}