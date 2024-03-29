package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

import Phase2.OrdersAndNotificationsSystem.models.request_bodies.BalanceUpdateRequest;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.Credentials;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.LoginResponse;
import Phase2.OrdersAndNotificationsSystem.services.account_services.AccountServices;
import Phase2.OrdersAndNotificationsSystem.services.security.JwtTokenUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
// run the server and go to http://localhost:8080/swagger-ui.html
// to see the generated documentation for the API
// you can also go to http://localhost:8080/v3/api-docs to see the raw documentation

/**
 * Controller class for managing user account-related operations.
 * Provides endpoints for user login, registration, and balance management.
 */
@RequestMapping("api/user")
@RestController
public class AccountController {

    @Autowired
    private AccountServices userServices;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    /**
     * Handles user login, generating a JWT token upon successful authentication.
     *
     * @param credentials The user credentials provided in the request body.
     * @return ResponseEntity containing a LoginResponse with the JWT token.
     * @throws GeneralException If there is an issue with user verification or authentication.
     */
    @GetMapping("/check")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login is successful"),
            @ApiResponse(responseCode = "406", description = "Invalid Credentials", content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "404", description = "User Not Found", content = @io.swagger.v3.oas.annotations.media.Content)

    })
    public ResponseEntity<LoginResponse> login(@RequestBody Credentials credentials) throws GeneralException {
        Account account = userServices.verifyUser(credentials);
        if (userServices.verifyUser(credentials) != null) {
            String token = jwtTokenUtil.generateToken(account.getUsername());
            return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
        } else
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid Credentials");
    }


    /**
     * Handles user registration, adding a new account to the system.
     *
     * @param account The account details provided in the request body.
     * @return ResponseEntity indicating the success of the registration.
     * @throws GeneralException If there is an issue with user registration.
     */
    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account is  added successfully"),
            @ApiResponse(responseCode = "406", description = "Username already exist"),
            @ApiResponse(responseCode = "403", description = "Email already exist")

    })
    public ResponseEntity<?> register(@RequestBody Account account) throws GeneralException {
        userServices.Registers(account);

        return new ResponseEntity<>("Account is  added successfully", HttpStatus.CREATED);
    }

    /**
     * Updates the balance of a user's account.
     *
     * @param request    The balance update request details provided in the request body.
     * @param authHeader The authorization header containing the JWT token.
     * @return ResponseEntity indicating the success of the balance update.
     * @throws GeneralException If there is an issue with updating the balance or handling the token.
     */

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balance is  updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid amount"),
            @ApiResponse(responseCode = "404", description = "User Not Found")

    })
    @PutMapping("/update-balance")
    public ResponseEntity<?> update(@RequestBody BalanceUpdateRequest request, @RequestHeader("Authorization") String authHeader) throws GeneralException {
        String token = authHeader.substring(7);
        String tokenUsername = jwtTokenUtil.getUsernameFromToken(token);
        Account account = userServices.getUserByUsername(tokenUsername);
        if (account == null)
            throw new GeneralException(HttpStatus.NOT_FOUND, "User Not Found");
        if (request.getAmount() < 0)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid amount");
        if (!userServices.updateBalance(account.getUsername(), request.getAmount()))
            return new ResponseEntity<>("Failed to update balance", HttpStatus.BAD_REQUEST
            );
        return new ResponseEntity<>("Balance is updated successfully", HttpStatus.OK);
    }


    /**
     * Retrieves the current balance of a user's account.
     *
     * @param authHeader The authorization header containing the JWT token.
     * @return ResponseEntity containing the current balance.
     * @throws GeneralException If there is an issue with retrieving the balance or handling the token.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balance is  updated successfully"),
            @ApiResponse(responseCode = "404", description = "User Not Found")

    })
    @GetMapping("/get-balance")
    public ResponseEntity<?> getBalance(@RequestHeader("Authorization") String authHeader) throws GeneralException {
        String token = authHeader.substring(7);
        String tokenUsername = jwtTokenUtil.getUsernameFromToken(token);
        Account account = userServices.getUserByUsername(tokenUsername);
        Map<String, Object> response = new HashMap<>();
        response.put("current_balance", account.getWalletBalance());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
