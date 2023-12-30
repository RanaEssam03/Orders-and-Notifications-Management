package Phase2.OrdersAndNotificationsSystem.services.account_services;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;

public interface AccountServices {


   public Account verifyUser(Credentials credentials) throws GeneralException;
   public Account Registers(Account account)throws GeneralException;

    public boolean updateBalance(String username, Double amount) throws GeneralException;

    public  Account getUserByUsername(String username) throws GeneralException;

    public boolean refund(Account account, Double amount) throws GeneralException;

}
