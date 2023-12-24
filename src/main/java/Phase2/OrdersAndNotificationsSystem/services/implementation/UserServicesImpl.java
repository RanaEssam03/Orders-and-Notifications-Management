package Phase2.OrdersAndNotificationsSystem.services.implementation;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.User;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.repositories.UsersRepository;
import Phase2.OrdersAndNotificationsSystem.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
   private UsersRepository userRepository;
    @Override
    public User verifyUser(Credentials credentials) throws GeneralException {
        if(credentials.getUsername().equals("") || credentials.getPassword().equals(""))
            throw new GeneralException("400","Invalid Credentials");

        Optional<User> user = userRepository.getUser(credentials);
        if(user.isPresent())
            return user.get();
        else
            throw new GeneralException("400","Invalid Credentials");

    }
}
