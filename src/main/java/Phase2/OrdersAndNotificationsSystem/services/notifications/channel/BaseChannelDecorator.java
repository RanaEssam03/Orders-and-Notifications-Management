package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.NotificationTypes;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

public abstract class BaseChannelDecorator extends MessageChannel {

    public abstract NotificationTypes sendMessage(Notification notification) throws GeneralException;
    public abstract boolean verifyContact(String contact);


}
