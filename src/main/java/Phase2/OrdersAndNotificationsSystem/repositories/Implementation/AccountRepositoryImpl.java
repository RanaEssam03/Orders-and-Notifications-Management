package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.Address;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository

public class AccountRepositoryImpl implements AccountRepo {

    ArrayList<Account> accounts = new ArrayList<>();
    public AccountRepositoryImpl() {
        accounts.add(new Account("ranaessam", "123456", "ranaessam03@gmail.com", "01145303111",new Address("Cairo","Mokatam","dd",42) , 1000.00));
        accounts.add(new Account("nooreyad", "12345$", "nooreyad1@gmail.com", "01146992561",new Address("Cairo","Mokatam","dd",2) , 10000.00));
        accounts.add(new Account("nourmuhamed", "12#456", "nourmuhamed4@gmail.com", "01155500847",new Address("Cairo","Mokatam","dd",16) , 2000.00));
        accounts.add(new Account("mariamhaitham", "1@3456", "mariamhaitham36@gmail.com", "01126134834",new Address("Cairo","Mokatam","dd",8) , 10000.50));
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
        System.out.println("Invalid Credentials");
       return null;
    }

    @Override
    public void addUser(Account user) {
        accounts.add(user);
    }

    @Override
    public void updateUser(Account user) {

    }
    @Override
    public void deleteUser(Account user) {
        accounts.remove(user);
    }
}
