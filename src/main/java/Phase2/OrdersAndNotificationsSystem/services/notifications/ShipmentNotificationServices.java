package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.repositories.implementation.NotificationRepositoryImpl;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.EmailChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.SMSChannel;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class ShipmentNotificationServices extends NotificationServices{
    Map<String, String> content = new HashMap<>();
    void initializeMap(){
        content.put("English", "Dear %s, your shipment of the order #%s is confirmed, keep track of your order. Please note that you have 10 minutes to cancel the shipment");
        content.put("German", "Sehr geehrter %s, Ihr Versand der Bestellung #%s wurde bestätigt. Behalten Sie den Überblick über Ihre Bestellung. Bitte beachten Sie, dass Sie 10 Minuten Zeit haben, um die Sendung zu stornieren");
        content.put("French", "Cher %s, votre expédition de la commande n°%s est confirmée, gardez une trace de votre commande. Veuillez noter que vous disposez de 10 minutes pour annuler l'envoi");
    }
    // TODO
    public ShipmentNotificationServices( NotificationsRepository notificationsRepository) {
        super( notificationsRepository);
        MessageChannel messageChannel = new EmailChannel();
        ((EmailChannel) messageChannel).createEmailChannel(new SMSChannel());
        super.createNotificationServicesChannel(messageChannel);
        initializeMap();
    }

    public Map<String, Integer> getCount() {
        Map<String, Integer> result = new HashMap<>();
        result.put(Collections.max(notificationsRepository.getShipmentCounter().entrySet(), Map.Entry.comparingByValue()).getKey(),
                Collections.max(notificationsRepository.getShipmentCounter().entrySet(), Map.Entry.comparingByValue()).getValue());
        return result;
    }

    @Override
    protected String createMessage(Order order) {
        Map<String, Integer> newCounter = notificationsRepository.getShipmentCounter();
        String lang = order.getAccount().getChosenLanguage();
        Integer count = newCounter.get(lang);
        if (count == null){
            newCounter.put(lang, 1);
        }
        else{
            newCounter.replace(lang, newCounter.get(lang), newCounter.get(lang)+1);
        }
        NotificationRepositoryImpl.setShipmentCounter(newCounter);
        return String.format(content.get(order.getAccount().getChosenLanguage()), order.getAccount().getUsername(), order.getId());
    }
}
