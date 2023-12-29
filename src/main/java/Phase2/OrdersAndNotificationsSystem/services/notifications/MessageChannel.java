package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Message;

public interface MessageChannel {
    public void sendMessage(Message message);
    public boolean verifyContact(String contact);

}
