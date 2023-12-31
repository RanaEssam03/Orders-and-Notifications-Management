package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.NotificationTypes;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;

public abstract class BaseChannelDecorator extends MessageChannel {
    MessageChannel messageChannel;

    public abstract NotificationTypes sendMessage(Notification notification) throws GeneralException;
    public abstract boolean verifyContact(String contact);
}
