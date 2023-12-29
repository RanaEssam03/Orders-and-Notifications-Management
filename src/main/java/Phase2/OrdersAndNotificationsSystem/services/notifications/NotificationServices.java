package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationDTO;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public abstract class NotificationServices {


    @Autowired
    private NotificationsRepository notificationsRepository;
    MessageChannel messageChannel;
    ArrayList<String> placeholdersModels = new ArrayList<>();


    protected abstract String createMessage(Order order);

    public NotificationServices(MessageChannel messageChannel, NotificationsRepository notificationsRepository) {
        this.messageChannel = messageChannel;
        this.notificationsRepository = notificationsRepository;
    }

    public boolean sendMessage(Order order) throws GeneralException {
        String message = createMessage(order);
        messageChannel.sendMessage(new Notification(message, order));
//        notificationsRepository.add(message);
        return true;
    }

    public ArrayList<NotificationDTO> getNotifications() {
        ArrayList<Notification> notifications = notificationsRepository.getAllNotifications();
        ArrayList<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            notificationDTOS.add(new NotificationDTO(notification));
        }


        return notificationDTOS;
    }
}
