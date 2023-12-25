package Phase2.OrdersAndNotificationsSystem.services.AccountServices;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.Address;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountServicesImp implements AccountServices {

    @Autowired
    private AccountRepo userRepository;

    @Override
    public Account verifyUser(Credentials credentials) throws GeneralException {
        if (credentials.getUsername().equals("") || credentials.getPassword().equals(""))
            throw new GeneralException("400", "Invalid Credentials");
        Account user = userRepository.getUser(credentials);
        if (user == null)
            throw new GeneralException("404", "User Not Found");
        return user;
    }

    public Account Registers(Account a) throws GeneralException {
        ArrayList<Account> userAccounts = userRepository.getAllUsers();
        for (Account account : userAccounts) {
            if(a.getEmail().equals(account.getEmail()))
                throw new GeneralException("404","Email already exist");
            else if (a.getUsername().equals(account.getUsername())){
                throw new GeneralException("404","Username already exist");
            }
        }
        userRepository.addUser(a);
        return a;
    }
}

