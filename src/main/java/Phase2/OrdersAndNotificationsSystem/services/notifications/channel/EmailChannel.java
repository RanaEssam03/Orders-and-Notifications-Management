package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.NotificationTypes;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.BaseChannelDecorator;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmailChannel extends BaseChannelDecorator {

    MessageChannel messageChannel;

    public void createEmailChannel(@Qualifier(value = "SMSChannel") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }
    @Override
    public NotificationTypes sendMessage(Notification notification) throws GeneralException {
        if (verifyContact(notification.getOrder().getAccount().getEmail())) {
            notification.setChannelType(notification.getChannelType()+ "Email ");
            if(messageChannel != null)
                return messageChannel.sendMessage(notification);

        } else {
            if(messageChannel != null) {
                messageChannel.sendMessage(notification);
                throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid phone number associated with this account, failed to send SMS!");
            }
            else
                return NotificationTypes.EMAIL;
        }
        return NotificationTypes.EMAIL;

    }

    @Override
    public boolean verifyContact(String contact) {
        if(contact.contains("@") && contact.contains(".com")){
            return true;
        }
        return false;
    }
}



