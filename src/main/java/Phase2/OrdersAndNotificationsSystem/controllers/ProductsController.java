package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.services.Products.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequestMapping("/api/products")
@RestController
public class ProductsController {

    @Autowired
    ProductServices productService;


    @GetMapping("/all")
    public ArrayList<Product> getAllProducts() throws GeneralException {
        return productService.getAllProducts();
    }

    @GetMapping("/all-by-category/{id}")
    public ArrayList<Product> getProductsByCategory(@PathVariable("id") Integer id) throws GeneralException {

        return productService.getProductsByCategory(id);
    }


}
