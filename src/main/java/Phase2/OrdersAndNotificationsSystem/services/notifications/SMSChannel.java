package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SMSChannel implements MessageChannel{


    @Autowired
    NotificationsRepository notificationRepo ;

    @Override
    public void sendMessage(Notification notification) throws GeneralException {
        notification.setMessageChannel(this);
        String mobile = notification.getOrder().getAccount().getPhoneNumber();
        if(verifyContact(notification.getOrder().getAccount().getPhoneNumber())){
            notificationRepo.add(notification);
           return;
        }
        else{
            throw new GeneralException( HttpStatus.CONFLICT, "Invalid contact number, failed to send SMS!");
        }

    }

    @Override
    public boolean verifyContact(String contact){
       if(contact.length() == 11 && contact.charAt(0) == '0' && contact.charAt(1) == '1'){
           return true;
       }
       return false;
    }

}

