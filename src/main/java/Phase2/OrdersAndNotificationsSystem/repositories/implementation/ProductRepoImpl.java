package Phase2.OrdersAndNotificationsSystem.repositories.implementation;

import Phase2.OrdersAndNotificationsSystem.models.Category;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import Phase2.OrdersAndNotificationsSystem.repositories.ProductRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.categories;
import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.products;

@Repository
public class ProductRepoImpl implements ProductRepo {
    static int productsCnt = 0;


    ProductRepoImpl() {
        Category category1 = new Category("Electronics");

        categories.add(category1);

        Category category2 = new Category("Clothing");
        categories.add(category2);

        Category category3 = new Category("Furniture");
        categories.add(category3);

        Category category4 = new Category("Grocery");
        categories.add(category4);

        Category category = new Category("Sports");
        categories.add(category);


        Product product1 = new Product();
        product1.setName("product1");
        product1.setVendor("vendor1");
        product1.setPrice(100.0);
        product1.setProductCount(3);


        ArrayList<Category> categories1 = new ArrayList<>();
        categories1.add(category4);

        product1.setCategories(categories1);

        products.add(product1);

        Product product2 = new Product();
        product2.setName("product2");
        product2.setVendor("vendor2");
        product2.setPrice(200.0);
        product2.setProductCount(2);

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

        Product product4 = new Product();
        product4.setName("product3");
        product4.setVendor("vendor3");
        product4.setPrice(300.0);
        product4.setProductCount(30);
        product4.getCategories().add(category);
        products.add(product4);

    }

    @Override
    public Optional<Product> getProduct(Integer serialNumber) {
        for (Product product : products) {
            if (product.getSerialNumber() == serialNumber) {
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
                if (product1.getProductCount() == 0) {
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

