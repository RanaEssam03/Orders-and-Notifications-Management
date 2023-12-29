package Phase2.OrdersAndNotificationsSystem.controllers;
import Phase2.OrdersAndNotificationsSystem.models.order.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.models.order.SimpleOrder;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.CompoundOrderRequest;
import Phase2.OrdersAndNotificationsSystem.models.request_bodies.OrderRequest;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.OrderResponse;
import Phase2.OrdersAndNotificationsSystem.services.AccountServices.AccountServices;
import Phase2.OrdersAndNotificationsSystem.services.Order.OrderServices;
import Phase2.OrdersAndNotificationsSystem.services.Products.ProductServices;
import Phase2.OrdersAndNotificationsSystem.services.security.JwtTokenUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

// run the server and go to http://localhost:8080/swagger-ui.html

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderServices orderServices;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    ProductServices productServices;

    @Autowired
    AccountServices userService;

    @ApiResponse(responseCode = "200", description = "Order is  added successfully and return the total price ")
    @PostMapping("/make-order")
    public OrderResponse makeOrder(@RequestBody OrderRequest order, @RequestHeader("Authorization") String authHeader ) throws GeneralException {
        String username = jwtTokenUtil.getUsernameFromToken(authHeader.substring(7));
        SimpleOrder simpleOrder = new SimpleOrder();
        simpleOrder.setAccount(userService.getUserByUsername(username));
        simpleOrder.setProducts(productServices.getProductsByID(order.getProductsIDs()));
        return new OrderResponse(orderServices.addOrder(simpleOrder).getPrice());
    }

    @ApiResponse(responseCode = "200", description = "Order is  added successfully and return the total price of the person who made the order ")
    @PostMapping("/make-compound-order")
    public OrderResponse makeCompoundOrder(@RequestBody CompoundOrderRequest order ,@RequestHeader("Authorization") String authHeader) throws GeneralException {
        if(authHeader == null){
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is missed!");
        }
        String username = jwtTokenUtil.getUsernameFromToken(authHeader.substring(7));
       CompoundOrder compoundOrder = new CompoundOrder();
       compoundOrder.setProducts(productServices.getProductsByID(order.getProductsIDs()));
       compoundOrder.setAccount(userService.getUserByUsername(username));
       for (String key : order.getOtherOrders().keySet()) {
           SimpleOrder simpleOrder = new SimpleOrder();
           simpleOrder.setProducts(productServices.getProductsByID(order.getOtherOrders().get(key).getProductsIDs()));
           simpleOrder.setAccount(userService.getUserByUsername(key));
           compoundOrder.getOrders().add(simpleOrder);
       }
        return new OrderResponse( orderServices.addOrder(compoundOrder).getPrice());
    }

    @GetMapping("/get-order/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Integer id) throws GeneralException {
        return orderServices.getOrder(id);
    }
}


