package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.NotificationTypes;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

public abstract class MessageChannel {
    NotificationTypes type;

    public NotificationTypes sendMessage(Notification notification) throws GeneralException {
        return type;
    }

    public abstract boolean verifyContact(String contact);
}
