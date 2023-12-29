package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import org.springframework.stereotype.Service;

import java.util.Queue;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.messagesQueue;

@Service
public class SMSChannel implements MessageChannel{




    @Override
    public void sendMessage(Notification notification){
        notification.setMessageChannel(this);
        String mobile = notification.getOrder().getAccount().getPhoneNumber();
        if(verifyContact(notification.getOrder().getAccount().getPhoneNumber())){
            System.out.println("SMS sent to " + mobile+ " with message: " + notification.getContent());
        }
        else{
            System.out.println("Invalid contact number, failed to send SMS!");
        }

        messagesQueue.add(notification);
    }

    @Override
    public boolean verifyContact(String contact){
       if(contact.length() == 11 && contact.charAt(0) == '0' && contact.charAt(1) == '1'){
           return true;
       }
       return false;
    }

}

