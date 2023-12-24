package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Message;

public class EmailChannel implements MessageChannel{
    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public boolean verifyContact(String contact) {
        return false;
    }
}
