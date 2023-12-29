package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import org.springframework.stereotype.Service;

@Service
public class EmailChannel implements MessageChannel{
    @Override
    public void sendMessage(Notification notification) {

    }

    @Override
    public boolean verifyContact(String contact) {
        return false;
    }
}
