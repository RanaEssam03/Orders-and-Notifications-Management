package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.EmailChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.SMSChannel;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlacementNotificationServices extends NotificationServices {
    static private Integer id;
    Map<String, String> content = new HashMap<>();
    void initializeMap(){
        content.put("English", "Dear %s, your order of %s is placed. thanks for using our online store :)");
        content.put("German", "Sehr geehrter %s, Ihre Bestellung von %s ist bestätigt. Vielen Dank, dass Sie unseren Online-Shop nutzen :)");
        content.put("French", "Cher %s, votre commande de %s est confirmée. merci d'utiliser notre boutique en ligne :)");
    }
    // TODO
    public PlacementNotificationServices( NotificationsRepository notificationsRepository) {
        super( notificationsRepository);
        MessageChannel messageChannel = new EmailChannel();
        super.createNotificationServicesChannel(messageChannel);
        initializeMap();
    }
    @Override
    protected String createMessage(Order order) {
        return String.format(content.get(order.getAccount().getChosenLanguage()), order.getAccount().getUsername(), order.getId());
    }
}
