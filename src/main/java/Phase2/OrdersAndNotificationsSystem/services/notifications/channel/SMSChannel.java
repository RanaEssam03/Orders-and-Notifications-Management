package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;
import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.NotificationTypes;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.smsStatistics;

@Service
public class SMSChannel extends BaseChannelDecorator {
    MessageChannel messageChannel;


    // Acts as a constructor for the decorator
    public void createSMSChannel(@Qualifier(value = "SMSChannel") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    @Override
    public NotificationTypes sendMessage(Notification notification) throws GeneralException {
        if (verifyContact(notification.getOrder().getAccount().getPhoneNumber())) {
            notification.setChannelType(notification.getChannelType()+ "SMS ");
            if(messageChannel != null){
                Integer count = smsStatistics.get(notification.getOrder().getAccount().getPhoneNumber());
                if (count == null){
                    smsStatistics.put(notification.getOrder().getAccount().getPhoneNumber(), 1);
                }
                else{
                    smsStatistics.put(notification.getOrder().getAccount().getPhoneNumber(),++count);
                }
                return messageChannel.sendMessage(notification);
            }
        }
        else {
            if(messageChannel != null) {
                messageChannel.sendMessage(notification);
                throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid phone number associated with this account, failed to send SMS!");
            }
            else
                return NotificationTypes.SMS;
        }
        return NotificationTypes.SMS;

    }

    @Override
    public boolean verifyContact(String contact) {
        return contact.length() == 11;
    }
}



