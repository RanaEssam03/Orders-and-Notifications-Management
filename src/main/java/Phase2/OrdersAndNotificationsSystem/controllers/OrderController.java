package Phase2.OrdersAndNotificationsSystem.controllers;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.order.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.CompoundOrderRequest;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.OrderRequest;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.OrderResponse;
import Phase2.OrdersAndNotificationsSystem.services.account_services.AccountServices;
import Phase2.OrdersAndNotificationsSystem.services.order.CompoundOrderServiceImpl;
import Phase2.OrdersAndNotificationsSystem.services.order.OrderServices;
import Phase2.OrdersAndNotificationsSystem.services.order.SimpleOrderServiceImpl;
import Phase2.OrdersAndNotificationsSystem.services.products.ProductServices;
import Phase2.OrdersAndNotificationsSystem.services.security.JwtTokenUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// run the server and go to http://localhost:8080/swagger-ui.html

@RestController
@RequestMapping("/api/order")
public class OrderController {


    OrderServices simpleOrderServices;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    ProductServices productServices;

    @Autowired
    AccountServices userService;


    OrderServices compoundOrderServices;

    public OrderController(CompoundOrderServiceImpl compoundOrderServices, SimpleOrderServiceImpl orderServices) {
        this.compoundOrderServices = compoundOrderServices;
        this.simpleOrderServices = orderServices;

    }

    @ApiResponse(responseCode = "200", description = "Order is  added successfully and return the total price ")
    @PostMapping("/place-order")
    public OrderResponse makeOrder(@RequestBody OrderRequest order, @RequestHeader("Authorization") String authHeader) throws GeneralException {
        String username = jwtTokenUtil.getUsernameFromToken(authHeader.substring(7));
        SimpleOrder simpleOrder = new SimpleOrder();
        simpleOrder.setAccount(userService.getUserByUsername(username));
        simpleOrder.setProducts(productServices.getProductsByID(order.getProductsIDs()));
        return new OrderResponse(simpleOrderServices.addOrder(simpleOrder).getId());

    }

    @PostMapping("/place-compound-order")
    public OrderResponse makeCompoundOrder(@RequestBody CompoundOrderRequest order, @RequestHeader("Authorization") String authHeader) throws GeneralException {
        String username = jwtTokenUtil.getUsernameFromToken(authHeader.substring(7));
        CompoundOrder compoundOrder = new CompoundOrder();
        compoundOrder.setAccount(userService.getUserByUsername(username));
        compoundOrder.setProducts(productServices.getProductsByID(order.getProductsIDs()));

        for (String key : order.getOtherOrders().keySet()) {
            Optional<Order> simpleOrder = simpleOrderServices.getOrder(order.getOtherOrders().get(key));
            if (simpleOrder.isEmpty()) {
                throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
            }

            if (simpleOrder.get().getAccount().getUsername() != key) {
                throw new GeneralException(HttpStatus.UNAUTHORIZED, "You are not authorized to confirm this order!");
            }
            if (simpleOrder.get().getStatus().equals("Confirmed")) {
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already confirmed!");
            }

            compoundOrder.getOrders().add(simpleOrder.get());
        }

        return new OrderResponse(compoundOrderServices.addOrder(compoundOrder).getId());

    }

    @PostMapping("/confirm-simple-order/{id}")
    public ResponseEntity<?> confirmOrder(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader) throws GeneralException {
        authHeader = authHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authHeader);
        if (username == null) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is missed!");
        }
        Optional<Order> order = simpleOrderServices.getOrder(id);
        if (order.isPresent()) {
            simpleOrderServices.confirmOrder(order.get());
        } else
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");


        if (!username.equals(order.get().getAccount().getUsername())) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "You are not authorized to confirm this order!");
        }

        return new ResponseEntity<>("Order is confirmed successfully", HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Order is  added successfully and return the total price of the person who made the order ")
    @PostMapping("/confirm-compound-order/{id}")
    public OrderResponse confirmCompoundOrder( @RequestHeader("Authorization") String authHeader, @PathVariable("id") Integer id) throws GeneralException {
        if (authHeader == null) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is missed!");
        }
        String username = jwtTokenUtil.getUsernameFromToken(authHeader.substring(7));
        Order compoundOrder  ;
        if(compoundOrderServices.getOrder(id).isEmpty())
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
        else
            compoundOrder = compoundOrderServices.getOrder(id).get();

        if(compoundOrder == null || compoundOrder instanceof SimpleOrder){
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
        }
        if (!username.equals(compoundOrder.getAccount().getUsername())) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "You are not authorized to confirm this order!");
        }
        if(compoundOrder.getStatus().equals("Confirmed")){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already confirmed!");
        }


        return new OrderResponse(compoundOrderServices.confirmOrder(compoundOrder).getId());
    }

    @GetMapping("/get-order/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Integer id) throws GeneralException {

        Optional<Order> order = simpleOrderServices.getOrder(id);
        if (order.isEmpty()) {

            if (order.isEmpty()) {
                throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
            }
        }

        Order o = order.get() instanceof CompoundOrder ? new CompoundOrder(order.get()) : new SimpleOrder(order.get());

        return Optional.of(o);

    }

    @DeleteMapping("/cancel-order/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader) throws GeneralException {
        authHeader = authHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authHeader);
        if (username == null) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is missed!");
        }
        Optional<Order> order = simpleOrderServices.getOrder(id);
        if (order.isEmpty()) {
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
        }
        if (order.get().getStatus().equals("Cancelled")) {
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled!");
        }
        if (!username.equals(order.get().getAccount().getUsername())) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "You are not authorized to cancel this order!");
        }

        if (order.isPresent()) {
            if (order.get() instanceof CompoundOrder) {
                compoundOrderServices.cancelOrder(order.get());
            } else {
                simpleOrderServices.cancelOrder(order.get());
            }
        } else
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");

        return new ResponseEntity<>("Order is cancelled successfully", HttpStatus.OK);
    }

    @DeleteMapping("/cancel-shipment/{id}")
    public ResponseEntity<?> cancelShipment(@PathVariable("id") Integer id, @RequestHeader("Authorization") String authHeader) throws GeneralException {
        authHeader = authHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authHeader);
        if (username == null) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is missed!");
        }
        Optional<Order> order = simpleOrderServices.getOrder(id);
        if (order.isEmpty()) {
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid order id");
        }
        if (order.get().getStatus().equals("Cancelled")) {
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is already cancelled!");
        }
        if (!username.equals(order.get().getAccount().getUsername())) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "You are not authorized to cancel this order!");
        }

        if(order.get().getStatus().equals("Placed")){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Order is not confirmed yet!");
        }
        if(order.get() instanceof  CompoundOrder)
            compoundOrderServices.cancelShipment(order.get());
        else
            simpleOrderServices.cancelShipment(order.get());

        return new ResponseEntity<>("Shipment is cancelled successfully", HttpStatus.OK);
    }
}


