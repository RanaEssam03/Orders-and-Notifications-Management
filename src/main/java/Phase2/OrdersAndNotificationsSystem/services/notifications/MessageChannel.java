package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;

public interface MessageChannel {
    public void sendMessage(Notification notification);
    public boolean verifyContact(String contact);

}
