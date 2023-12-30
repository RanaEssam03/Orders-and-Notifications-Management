package Phase2.OrdersAndNotificationsSystem.services.account_services;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountServicesImp implements AccountServices {

    @Autowired
    private AccountRepo userRepository;

    @Override
    public Account verifyUser(Credentials credentials) throws GeneralException {
        if (credentials.getUsername().equals("") || credentials.getPassword().equals(""))
            throw new GeneralException(HttpStatus.NOT_ACCEPTABLE, "Invalid Credentials");
        Account user = userRepository.getUserByUsername(credentials.getUsername());
        if (user == null)
            throw new GeneralException(HttpStatus.NOT_FOUND, "User Not Found");
        if (!user.getPassword().equals(credentials.getPassword()))
            throw new GeneralException(HttpStatus.NOT_ACCEPTABLE, "Invalid Credentials");
        return user;
    }

    public Account Registers(Account a) throws GeneralException {
        ArrayList<Account> userAccounts = userRepository.getAllUsers();
        for (Account account : userAccounts) {
            if(a.getEmail().equals(account.getEmail()))
                throw new GeneralException(HttpStatus.FORBIDDEN,"Email already exist");
            else if (a.getUsername().equals(account.getUsername())){
                throw new GeneralException(HttpStatus.NOT_ACCEPTABLE,"Username already exist");
            }
        }
        userRepository.addUser(a);
        return a;
    }

    public boolean updateBalance(String username, Double amount) throws GeneralException {

        Account user = userRepository.getUserByUsername(username);

        user.setWalletBalance(user.getWalletBalance() + amount);
        userRepository.updateUser(user);
        return true;
    }

    @Override
    public Account getUserByUsername(String username) throws GeneralException {
        Account user = userRepository.getUserByUsername(username);
        if (user == null)
            throw new GeneralException(HttpStatus.NOT_FOUND, "User Not Found");
        return user;
    }

    @Override
    public boolean refund(Account account, Double amount) throws GeneralException {
        if(account == null)
            throw new GeneralException(HttpStatus.NOT_FOUND, "User Not Found");
        if(amount < 0)
            throw new GeneralException(HttpStatus.NOT_ACCEPTABLE, "Invalid amount");
        account.setWalletBalance(account.getWalletBalance() + amount);
        return true;
    }


}

