package Phase2.OrdersAndNotificationsSystem.repositories.Implementation;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.repositories.ProductRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class ProductRepoImpl implements ProductRepo {
    ArrayList<Product> products = new ArrayList<Product>();
    static int productsCnt = 0;


    ProductRepoImpl(){
        Category category = new Category();
        category.setName("Electronics");



        Category category1 = new Category();
        category1.setName("Clothing");


        Category category2 = new Category();
        category2.setName("Furniture");


        Category category3 = new Category();
        category3.setName("Grocery");


       Category category4 = new Category();
        category4.setName("Books");




        Product product1 = new Product();
            product1.setName("product1");
            product1.setVendor("vendor1");
            product1.setPrice(100.0);
            product1.setProductCount(10);

            ArrayList<Category> categories1 = new ArrayList<>();
            categories1.add(category4);

            product1.setCategories(categories1);

            products.add(product1);

            Product product2 = new Product();
            product2.setName("product2");
            product2.setVendor("vendor2");
            product2.setPrice(200.0);
            product2.setProductCount(20);

            ArrayList<Category> categories2 = new ArrayList<>();
            categories2.add(category);


            product2.setCategories(categories2);
            products.add(product2);

            Product product3 = new Product();
            product3.setName("product3");
            product3.setVendor("vendor3");
            product3.setPrice(300.0);
            product3.setProductCount(30);

            ArrayList<Category> categories3 = new ArrayList<>();
        categories3.add(category1);
        categories3.add(category2);
        categories3.add(category);

            product3.setCategories(categories3);
            products.add(product3);

    }

    @Override
    public Optional<Product> getProduct(Integer serialNumber) {
        for (Product product : products) {
            if (product.getSerialNumber()== serialNumber) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }



    @Override
    public void addProduct(Product product) {
        products.add(product);

    }

    @Override
    public boolean updateProduct(Product product) {
        for (Product product1 : products) {
            if (product1.getSerialNumber().equals(product.getSerialNumber())) {
                product1.setProductCount(product.getProductCount());
                if(product1.getProductCount() == 0){
                    products.remove(product1);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Optional<ArrayList<Product>> getAllProducts() {
        return Optional.of(products);
    }

    @Override
    public ArrayList<Product> getProductsByCategory(Category category) {
           ArrayList<Product> productsByCategory = new ArrayList<Product>();
            for (Product product : products) {
                for (Category category1 : product.getCategories()) {
                    if (category1.getId() == category.getId()) {
                        productsByCategory.add(product);
                        break;
                    }
                }
            }
            return productsByCategory;
    }


}

