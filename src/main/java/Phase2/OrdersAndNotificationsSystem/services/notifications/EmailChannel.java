package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailChannel implements MessageChannel{

    @Autowired
    NotificationsRepository notificationRepo ;



    @Override
    public void sendMessage(Notification notification) {
        notification.setMessageChannel(this);
        String email = notification.getOrder().getAccount().getEmail();
        if(verifyContact(email)){
            notificationRepo.add(notification);
            return;
        }
        else{
            throw new RuntimeException("Invalid email associated with this account, failed to send email!");
        }

    }

    @Override
    public boolean verifyContact(String contact) {
        if(contact.contains("@") && contact.contains(".com")){
            return true;
        }
        return false;
    }
}
