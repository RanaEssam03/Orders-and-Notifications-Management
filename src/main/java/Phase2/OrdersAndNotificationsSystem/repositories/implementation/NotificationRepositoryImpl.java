package Phase2.OrdersAndNotificationsSystem.repositories.implementation;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.messagesQueue;

@Repository
public class NotificationRepositoryImpl implements NotificationsRepository {

    static int idCounter = 0;
    @Setter
    static Map<String, Integer> shipmentCounter = new HashMap<>();
    @Setter
    static Map<String, Integer> cancellationCounter = new HashMap<>();
    @Setter
    static Map<String, Integer> confirmationCounter = new HashMap<>();
    @Override
    public Notification add(Notification notification) {
        notification.setId(++idCounter);
        messagesQueue.add(notification);
        return notification;
    }

    @Override
    public ArrayList<Notification> getAll() {
        return messagesQueue;
    }

    @Override
    public boolean delete(Notification message) {
        for (Notification msg : messagesQueue){
            if (msg == message){
                messagesQueue.remove(msg);
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Integer> getConfirmationCounter() {
        return confirmationCounter;
    }

    @Override
    public Map<String, Integer> getShipmentCounter() {
        return shipmentCounter;
    }
    @Override
    public Map<String, Integer> getCancellationCounter() {
        return cancellationCounter;
    }

    @Override
    public ArrayList<Notification> getAllNotifications() {
        return messagesQueue;
    }

}
