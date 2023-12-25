package Phase2.OrdersAndNotificationsSystem.repositories;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;

import java.util.ArrayList;

public interface AccountRepo {
    public ArrayList<Account> getAllUsers();
    public Account getUser(Credentials credentials);
    public void addUser(Account user);
    public void updateUser(Account user);
    public void deleteUser(Account user);
}
