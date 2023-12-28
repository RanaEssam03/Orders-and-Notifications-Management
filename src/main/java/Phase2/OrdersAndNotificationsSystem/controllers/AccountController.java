package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

import Phase2.OrdersAndNotificationsSystem.models.request_bodies.BalanceUpdateRequest;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.services.AccountServices.AccountServices;
import Phase2.OrdersAndNotificationsSystem.services.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
public class AccountController {

    @Autowired
    private AccountServices userServices;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/check")
    public String login(@RequestBody Credentials credentials) throws GeneralException {

        Account account = userServices.verifyUser(credentials);
        if( userServices.verifyUser(credentials) != null) {
            String token = jwtTokenUtil.generateToken(account.getUsername());
            return token;
        }
           else
            throw new GeneralException(HttpStatus.NOT_FOUND,"Invalid Credentials");
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) throws GeneralException {
        userServices.Registers(account);
        return new ResponseEntity<>("Account is  added successfully", HttpStatus.OK);
    }

    @PutMapping("/update-balance")
    public ResponseEntity<?> update(@RequestBody BalanceUpdateRequest request) throws GeneralException {
        if(!userServices.updateBalance(request.getUsername(), request.getAmount()))
            return new ResponseEntity<>("Failed to update balance", HttpStatus.BAD_REQUEST
            );

        return new ResponseEntity<>("Account is updated successfully", HttpStatus.OK);
    }
}
