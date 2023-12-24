package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Message;
import Phase2.OrdersAndNotificationsSystem.models.Order;

public interface MessageChannel {
    public void sendMessage(Message message);
    public boolean verifyContact(String contact);

}
