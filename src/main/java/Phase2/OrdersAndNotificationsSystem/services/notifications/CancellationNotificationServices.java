package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.repositories.implementation.NotificationRepositoryImpl;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.EmailChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CancellationNotificationServices extends NotificationServices{
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
    public CancellationNotificationServices(NotificationsRepository notificationsRepository) {
        super(notificationsRepository);
        MessageChannel messageChannel = new EmailChannel();
        super.createNotificationServicesChannel(messageChannel);
        initializeMap();
    }

    public Map<String, Integer> getCount() {
        Map<String, Integer> result = new HashMap<>();
        result.put(Collections.max(notificationsRepository.getCancellationCounter().entrySet(), Map.Entry.comparingByValue()).getKey(),
                Collections.max(notificationsRepository.getCancellationCounter().entrySet(), Map.Entry.comparingByValue()).getValue());
        return result;
    }

    @Override
    protected String createMessage(Order order) {
        Map<String, Integer> newCounter = notificationsRepository.getCancellationCounter();
        String lang = order.getAccount().getChosenLanguage();
        Integer count = newCounter.get(lang);
        if (count == null){
            newCounter.put(lang, 1);
        }
        else{
            newCounter.replace(lang, newCounter.get(lang), newCounter.get(lang)+1);
        }
        NotificationRepositoryImpl.setConfirmationCounter(newCounter);
        return String.format(content.get(order.getAccount().getChosenLanguage()), order.getAccount().getUsername(), order.getId());
    }
}
