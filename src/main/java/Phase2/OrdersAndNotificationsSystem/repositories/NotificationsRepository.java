package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.ShipmentNotificationServices;

import java.util.ArrayList;


public interface NotificationsRepository {
    public Notification add(Notification notification);

    public ArrayList<Notification> getAll();

    public boolean delete(Notification message);
}
