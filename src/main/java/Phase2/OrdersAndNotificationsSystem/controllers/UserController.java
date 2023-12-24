package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.User;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/check")
    public User login(@RequestBody Credentials credentials) throws GeneralException {
        return userServices.verifyUser(credentials);
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) throws GeneralException {
        return new User();
    }


}
