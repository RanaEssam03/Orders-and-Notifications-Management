package Phase2.OrdersAndNotificationsSystem.repositories.implementation;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.Address;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.accounts;

@Repository

public class AccountRepositoryImpl implements AccountRepo {


    public AccountRepositoryImpl() {
        accounts.add(new Account("ranaessam", "12345", "ranaessam@gmail.com", "01114335538",new Address("Mokattam","Cairo","dd",5) , 300.0, "English"));
        accounts.add(new Account("nooreyad", "12345", "nooreyad1@gmail.com", "01146992561",new Address("maadi","Cairo","dd",2) , 4000.00, "English") );
        accounts.add(new Account("nourmuhamed", "12345", "nourmuhamed4@gmail.com", "01155500847",new Address("Dokki","Cairo","dd",16) , 400.00, "French"));
        accounts.add(new Account("mariamhaitham", "12345", "mariamhaitham36@gmail.com", "01126134834",new Address("Mokattam","Cairo","dd",8) , 500.50, "German"));
    }


    @Override
    public ArrayList<Account> getAllUsers() {
        return accounts;
    }

    @Override
    public Account getUser(Credentials credentials)  {
        for (Account user : accounts) {
            if (user.getUsername().equals(credentials.getUsername()) && user.getPassword().equals(credentials.getPassword())) {
                return user;
            }
        }
       return null;
    }

    @Override
    public void addUser(Account user) {
        accounts.add(user);
    }

    @Override
    public void updateUser(Account user) {
        for (Account u : accounts) {
            if (u.getUsername().equals(user.getUsername())) {
                u = user;
            }
        }

    }
    @Override
    public void deleteUser(Account user) {
        accounts.remove(user);
    }

    @Override
    public Account getUserByUsername(String username) {
        for (Account user : accounts) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
