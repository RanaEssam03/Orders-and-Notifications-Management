package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.repositories.implementation.NotificationRepositoryImpl;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.EmailChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlacementNotificationServices extends NotificationServices {
    Map<String, String> content = new HashMap<>();
    void initializeMap(){
        content.put("English", "Dear %s, your order of ID: %s is placed. Thanks for using our online store :)" +
                " You have 1 day to cancel your order");
        content.put("German", "Sehr geehrter %s, Ihre Bestellung mit der ID: %s wurde aufgegeben. Vielen Dank, dass Sie unseren Online-Shop nutzen :) " +
                "Sie haben 1 Tag Zeit, Ihre Bestellung zu stornieren");
        content.put("French", "Cher %s, votre commande d'ID : %s a été passée. Merci d'utiliser notre boutique en ligne :)" +
                " Vous avez 1 jour pour annuler votre commande");
    }

    public PlacementNotificationServices( NotificationsRepository notificationsRepository) {
        super( notificationsRepository);
        MessageChannel messageChannel = new EmailChannel();
        super.createNotificationServicesChannel(messageChannel);
        initializeMap();
    }

    @Override
    public Integer getCount() {
        return notificationsRepository.getConfirmationCounter();
    }

    @Override
    protected String createMessage(Order order) {
        NotificationRepositoryImpl.setConfirmationCounter(notificationsRepository.getConfirmationCounter()+1);
        return String.format(content.get(order.getAccount().getChosenLanguage()), order.getAccount().getUsername(), order.getId());
    }
}
