package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.Message;
import Phase2.OrdersAndNotificationsSystem.models.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public abstract class NotificationServices {

    @Autowired
    private NotificationsRepository notificationsRepository;
    MessageChannel messageChannel;
    ArrayList<String> placeholdersModels = new ArrayList<>();

    protected abstract String createMessage(Order order);

    public boolean sendMessage(Order order, String receiver) throws GeneralException {
        Message message = new Message(createMessage(order), receiver);
        if(messageChannel.verifyContact(receiver)){
            messageChannel.sendMessage(message);
        }
        else{
            throw new GeneralException(HttpStatus.NOT_FOUND, "Contact not found");
        }
        notificationsRepository.add(message);
        return true;
    }
}
