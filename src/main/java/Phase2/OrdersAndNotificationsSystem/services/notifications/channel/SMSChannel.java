package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;
import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.NotificationTypes;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SMSChannel extends BaseChannelDecorator {

    MessageChannel messageChannel;


    // Acts as a constructor for the decorator
    public void createSMSChannel(@Qualifier(value = "EmailChannel") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    @Override
    public NotificationTypes sendMessage(Notification notification) throws GeneralException {
        if (verifyContact(notification.getOrder().getAccount().getPhoneNumber())) {
            notification.setChannelType(notification.getChannelType()+ "SMS ");
            if(messageChannel != null)
                return messageChannel.sendMessage(notification);

        } else {
            if(messageChannel == null)
                return
            messageChannel.sendMessage(notification);
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid phone number associated with this account, failed to send SMS!");
        }
        return NotificationTypes.SMS;

    }

    @Override
    public boolean verifyContact(String contact) {
        if(contact.length() == 11){
            return true;
        }
        return false;
    }
}



