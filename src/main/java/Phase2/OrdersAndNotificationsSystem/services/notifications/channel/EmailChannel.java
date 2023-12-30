package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.NotificationTypes;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Map;
import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.emailStatistics;

@Service
public class EmailChannel extends BaseChannelDecorator {

    MessageChannel messageChannel;

    public void createEmailChannel(@Qualifier(value = "EmailChannel") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }
    @Override
    public NotificationTypes sendMessage(Notification notification) throws GeneralException {
        if (verifyContact(notification.getOrder().getAccount().getEmail())) {
            notification.setChannelType(notification.getChannelType()+ "Email ");
            if(messageChannel != null){
                Integer count = emailStatistics.get(notification.getOrder().getAccount().getEmail());
                if (count == null){
                    emailStatistics.put(notification.getOrder().getAccount().getEmail(), 1);
                }
                else{
                    emailStatistics.put(notification.getOrder().getAccount().getEmail(),++count);
                }
                return messageChannel.sendMessage(notification);
            }
        } else {
            if(messageChannel != null) {
                messageChannel.sendMessage(notification);
                throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid Email associated with this account, failed to send email!");
            }
            else
                return NotificationTypes.EMAIL;
        }
        return NotificationTypes.EMAIL;
    }

    @Override
    public boolean verifyContact(String contact) {
        return contact.contains("@") && contact.contains(".com");
    }
}



