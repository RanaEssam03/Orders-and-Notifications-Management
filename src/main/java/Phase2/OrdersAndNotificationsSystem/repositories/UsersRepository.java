package Phase2.OrdersAndNotificationsSystem.repositories;
import Phase2.OrdersAndNotificationsSystem.models.User;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;

import java.util.ArrayList;
import java.util.Optional;

// CRUD operations
public interface UsersRepository {
    Optional<ArrayList<User>> getAllUsers();
    Optional<User> getUser(Credentials credentials) ;
    Optional<User> addUser(User user);
    void updateUser(User user);
    void deleteUser(String username);
}
