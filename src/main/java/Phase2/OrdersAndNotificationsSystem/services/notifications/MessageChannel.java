package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

public interface MessageChannel {
    public void sendMessage(Notification notification) throws GeneralException;
    public boolean verifyContact(String contact);

}
