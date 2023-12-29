package Phase2.OrdersAndNotificationsSystem.controllers;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.services.Products.ProductServices;
import Phase2.OrdersAndNotificationsSystem.services.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
// run the server and go to http://localhost:8080/swagger-ui.html

@RequestMapping("/api/products")
@RestController
public class ProductsController {

    @Autowired
    ProductServices productService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @GetMapping("/get-all")
    public ArrayList<Product> getAllProducts(@RequestHeader("Authorization") String authHeader ) throws GeneralException {

         jwtTokenUtil.getUsernameFromToken(authHeader.substring(7));
        return productService.getAllProducts();
    }

    @GetMapping("/all-by-category/{id}")
    public ArrayList<Product> getProductsByCategory(@PathVariable("id") Integer id) throws GeneralException {

        return productService.getProductsByCategory(id);
    }


}
