package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationDTO;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public abstract class NotificationServices {
    @Autowired
    protected NotificationsRepository notificationsRepository;
    @Getter
    MessageChannel messageChannel;
    ArrayList<String> placeholdersModels = new ArrayList<>();


    protected abstract String createMessage(Order order);

    public NotificationServices( NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    public void createNotificationServicesChannel(@Qualifier(value = "SMSChannel") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    public boolean sendMessage(Order order) throws GeneralException {
        String message = createMessage(order);
        Notification notification = new Notification(message, order);
        messageChannel.sendMessage(notification);
        notificationsRepository.add(notification);
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

    public abstract Integer getCount();

}
