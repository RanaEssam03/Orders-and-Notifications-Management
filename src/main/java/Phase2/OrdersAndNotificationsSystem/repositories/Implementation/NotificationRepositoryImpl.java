package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;

import Phase2.OrdersAndNotificationsSystem.models.Message;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Queue;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.messagesQueue;

@Repository
public class NotificationRepositoryImpl implements NotificationsRepository {

    static int idCounter = 0;
    @Override
    public Message add(Message message) {
        message.setId(idCounter++);
        messagesQueue.add(message);
        return message;
    }

    @Override
    public ArrayList<Message> getAll() {
        return messagesQueue;
    }

    @Override
    public boolean delete(Message message) {
        return false;
    }
}
