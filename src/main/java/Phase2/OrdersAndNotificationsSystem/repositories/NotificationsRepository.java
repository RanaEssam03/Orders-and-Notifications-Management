package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Message;
import Phase2.OrdersAndNotificationsSystem.services.notifications.MessageChannel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Queue;


public interface NotificationsRepository {
    public Message add(Message message);

    public ArrayList<Message> getAll();

    public boolean delete(Message message);
}
