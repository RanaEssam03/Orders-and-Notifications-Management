package Phase2.OrdersAndNotificationsSystem.services.Products;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.order.CompoundOrder;
import Phase2.OrdersAndNotificationsSystem.models.order.Order;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.InventoryImpl;
import Phase2.OrdersAndNotificationsSystem.repositories.Implementation.ProductRepoImpl;
import Phase2.OrdersAndNotificationsSystem.repositories.InventoryRepo;
import Phase2.OrdersAndNotificationsSystem.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServicesImp implements ProductServices {



    @Autowired
    ProductRepo productRepo;

    @Autowired
    InventoryImpl inventoryRepo;



    @Override
    public ArrayList<Product> getAllProducts() throws GeneralException {
        Optional<ArrayList<Product>> products = productRepo.getAllProducts();
        if (products.isPresent())
            return products.get();
        else
            throw new GeneralException(HttpStatus.ACCEPTED, "No products found");
    }



    @Override
    public ArrayList<Product> getProductsByCategory(Integer id) throws GeneralException {
        Category category = inventoryRepo.getCategory(id);
        if(category == null)
            throw new GeneralException(HttpStatus.NOT_FOUND, "Invalid category id");
        ArrayList<Product> products = productRepo.getProductsByCategory(category );
        if (products.isEmpty()) {
            throw new GeneralException(HttpStatus.ACCEPTED, "No products found for this category");
        } else
            return products;

    }

    @Override
    public ArrayList<Product> getProductsByID(ArrayList<Integer> IDs) throws GeneralException {
        ArrayList<Product> products = new ArrayList<>();
        if(IDs == null || IDs.isEmpty())
            throw new GeneralException(HttpStatus.NOT_FOUND, "No products are found" );

        for (Integer id : IDs){
            Optional<Product> p = productRepo.getProduct(id);
            if (p.isEmpty()){
                throw new GeneralException(HttpStatus.NOT_FOUND, "Some products are not found!");
            }
            else {
                products.add(p.get());
            }

        }
        return products;
    }

    @Override
    public boolean reduceProductQuantity(Product product, Integer quantity) throws GeneralException {
        if(product == null || quantity == null)
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid product or quantity");
        else {
            if (product.getProductCount() < quantity)
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Invalid quantity");
            else {
                product.setProductCount(product.getProductCount() - quantity);
                return productRepo.updateProduct(product);
            }
        }
    }

//    public boolean validProductCount(Order order) throws GeneralException {
//        Map<Product, Integer> productCount = new HashMap<>();
//        ArrayList<Product > products;
//        if(order instanceof CompoundOrder){
//            ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
//            for(Order currOrder : orders){
//                products = currOrder.getProducts();
//                for (Product product : products) {
//                    productCount.put(product, 0);
//                }
//                for(Product p :products){
//                    productCount.put(p ,productCount.get(p) + 1);
//                    if(productCount.get(p) > p.getProductCount()){
//                        throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough products in stock");
//                    }
//                }
//            }
//        }
//        products = order.getProducts();
//        for (Product product : products) {
//            productCount.put(product, 0);
//        }
//        for(Product p :products){
//            productCount.put(p ,productCount.get(p) + 1);
//            if(productCount.get(p) > p.getProductCount()){
//                throw new GeneralException(HttpStatus.BAD_REQUEST, "Not enough products in stock");
//            }
//        }
//        return true;
//
//    }
}
