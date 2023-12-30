package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Notification;

import java.util.ArrayList;


public interface NotificationsRepository {
    public Notification add(Notification notification);

    public ArrayList<Notification> getAll();

    public boolean delete(Notification message);
    public int getCancellationCounter();
    public int getConfirmationCounter();
    public int getShipmentCounter();
    public ArrayList<Notification> getAllNotifications();
}
