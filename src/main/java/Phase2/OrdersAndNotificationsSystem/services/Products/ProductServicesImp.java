package Phase2.OrdersAndNotificationsSystem.services.Products;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.InventoryImpl;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.ProductRepoImpl;
import Phase2.OrdersAndNotificationsSystem.repositories.InventoryRepo;
import Phase2.OrdersAndNotificationsSystem.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductServicesImp implements ProductServices {



    @Autowired
    ProductRepoImpl productRepo;

    @Autowired
    InventoryImpl inventoryRepo;



    @Override
    public ArrayList<Product> getAllProducts() throws GeneralException {
        Optional<ArrayList<Product>> products = productRepo.getAllProducts();
        if (products.isPresent())
            return products.get();
        else
            throw new GeneralException("401", "No products found");
    }



    @Override
    public ArrayList<Product> getProductsByCategory(Integer id) throws GeneralException {
        Category category = inventoryRepo.getCategory(id);
        if(category == null)
            throw new GeneralException("401", "Invalid category id");
        ArrayList<Product> products = productRepo.getProductsByCategory(category );
        if (products.isEmpty()) {
            throw new GeneralException("401", "No products found for this category");
        } else
            return products;

    }
}
