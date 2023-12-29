package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class ShipmentNotificationServices extends NotificationServices{

    Map<String, String> content = new HashMap<>();
    void initializeMap(){
        content.put("English", "Dear %s, your shipment of the order #%s is confirmed, keep track of your order.");
        content.put("German", "Sehr geehrter %s, Ihr Versand der Bestellung #%s wurde bestätigt. Behalten Sie den Überblick über Ihre Bestellung.");
        content.put("French", "Cher %s, votre expédition de la commande n°%s est confirmée, gardez une trace de votre commande.");
    }
    public ShipmentNotificationServices(EmailChannel messageChannel, NotificationsRepository notificationsRepository) {
        super(messageChannel, notificationsRepository);
        initializeMap();
    }
    @Override
    protected String createMessage(Order order) {
        return String.format(content.get(order.getAccount().getChosenLanguage()), order.getAccount().getUsername(), order.getId());
    }
}
