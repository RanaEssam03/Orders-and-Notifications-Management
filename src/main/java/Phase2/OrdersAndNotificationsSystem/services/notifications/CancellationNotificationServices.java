package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.EmailChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CancellationNotificationServices extends NotificationServices{
    static private Integer count = 0;
    Map<String, String> content = new HashMap<>();

    void initializeMap(){
        content.put("English", "Dear %s, we are sorry you had to cancel your order of ID: %s :( " +
                "we are looking forward to seeing you soon :D");
        content.put("German", "Lieber %s, es tut uns leid, dass Sie Ihre Bestellung von ID: %s stornieren mussten :( " +
                "wir freuen uns auf ein baldiges Wiedersehen :D");
        content.put("French", "Cher %s, nous sommes désolés que vous ayez dû annuler votre commande de pièce d'identité : %s :( " +
                "nous avons hâte de vous revoir bientôt :D");
    }
    // TODO
    public CancellationNotificationServices( NotificationsRepository notificationsRepository) {
        super(notificationsRepository);
        MessageChannel messageChannel = new EmailChannel();
        super.createNotificationServicesChannel(messageChannel);
        initializeMap();
        ++count;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    protected String createMessage(Order order) {
        return String.format(content.get(order.getAccount().getChosenLanguage()), order.getAccount().getUsername(), order.getId());
    }
}
