package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.services.products.ProductServices;
import Phase2.OrdersAndNotificationsSystem.services.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Controller class for managing product-related operations.
 * Provides endpoints to retrieve product information.
 */
@RequestMapping("/api/products")
@RestController
public class ProductsController {

    @Autowired
    ProductServices productService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * Retrieves all products from the system.
     *
     * @param authHeader The authorization header containing the JWT token.
     * @return An ArrayList of Product objects representing all products in the system.
     * @throws GeneralException If there is an issue with the authorization token.
     */
    @GetMapping("/get-all")
    public ArrayList<Product> getAllProducts(@RequestHeader("Authorization") String authHeader ) throws GeneralException {
        jwtTokenUtil.getUsernameFromToken(authHeader.substring(7));
        return productService.getAllProducts();
    }

    /**
     * Retrieves all products belonging to a specific category.
     *
     * @param id The ID of the category for which products are to be retrieved.
     * @return An ArrayList of Product objects representing products in the specified category.
     * @throws GeneralException If there is an issue with the retrieval of products by category.
     */
    @GetMapping("/all-by-category/{id}")
    public  Map<Object, Object> getProductsByCategory(@PathVariable("id") Integer id) throws GeneralException {
        Map<Object, Object> mp  = new java.util.HashMap<>();
        ArrayList<Product> products = productService.getProductsByCategory(id);
        mp.put("products count of this category", products.size());
       mp.put("products", products);
        return mp;
    }
}
