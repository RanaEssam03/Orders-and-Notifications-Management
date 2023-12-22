package Phase2.OrdersAndNotificationsSystem.repositories;
import Phase2.OrdersAndNotificationsSystem.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.User;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface UsersRepository {
    ArrayList<User> getAllUsers();
    User getUser(Credentials credentials) ;
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String username);



}
