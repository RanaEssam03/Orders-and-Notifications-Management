package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.ShipmentNotificationServices;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public abstract class NotificationServices {

    @Autowired
    private NotificationsRepository notificationsRepository;
    MessageChannel messageChannel;

    public NotificationServices(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    ArrayList<String> placeholdersModels = new ArrayList<>();

    protected abstract String createMessage(Order order);

    public boolean sendMessage(Order order) throws GeneralException {
        String message = createMessage(order);
        messageChannel.sendMessage(new Notification(message, order));
//        notificationsRepository.add(message);
        return true;
    }
}
