package Phase2.OrdersAndNotificationsSystem.repositories.implementation;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.messagesQueue;

@Repository
public class NotificationRepositoryImpl implements NotificationsRepository {

    static int idCounter = 0;
    @Setter
    static int shipmentCounter = 0;
    @Setter
    static int cancellationCounter = 0;
    @Setter
    static int confirmationCounter = 0;
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
    public int getConfirmationCounter() {
        return confirmationCounter;
    }

    @Override
    public int getShipmentCounter() {
        return shipmentCounter;
    }
    @Override
    public int getCancellationCounter() {
        return cancellationCounter;
    }

    @Override
    public ArrayList<Notification> getAllNotifications() {
        return messagesQueue;
    }

}
