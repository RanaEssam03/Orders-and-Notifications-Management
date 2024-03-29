package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import java.util.Map;
import java.util.ArrayList;


public interface NotificationsRepository {
    public Notification add(Notification notification);

    public ArrayList<Notification> getAll();

    public boolean delete(Notification message);
    public Map<String, Integer> getCancellationCounter();
    public Map<String, Integer> getConfirmationCounter();
    public Map<String, Integer> getShipmentCounter();
    public ArrayList<Notification> getAllNotifications();
}
