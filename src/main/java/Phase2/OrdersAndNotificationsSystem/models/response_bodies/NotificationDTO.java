package Phase2.OrdersAndNotificationsSystem.models.response_bodies;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import lombok.Data;

@Data
public class NotificationDTO {
    /**
     * The type of message channel for the notification (e.g., "Email").
     */
    private String message_channel_type;

    /**
     * The content of the notification message.
     */
    private String content;

    /**
     * The ID of the order associated with the notification.
     */
    private Integer orderID;

    /**
     * The username of the account associated with the order and notification.
     */
    private String username;

    /**
     * Constructs a NotificationDTO with the specified parameters.
     *
     * @param messageChannel The type of message channel for the notification.
     * @param content        The content of the notification message.
     * @param orderID        The ID of the order associated with the notification.
     * @param username       The username of the account associated with the order and notification.
     */
    public NotificationDTO(String messageChannel, String content, Integer orderID, String username) {
        this.message_channel_type = messageChannel;
        this.content = content;
        this.orderID = orderID;
        this.username = username;
    }

    /**
     * Constructs a NotificationDTO based on a Notification entity.
     *
     * @param notification The Notification entity from which to extract information.
     */
    public NotificationDTO(Notification notification) {
        this.message_channel_type = notification.getChannelType();
        this.content = notification.getContent();
        this.orderID = notification.getOrder().getId();
        this.username = notification.getOrder().getAccount().getUsername();
    }
}