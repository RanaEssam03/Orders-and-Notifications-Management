package Phase2.OrdersAndNotificationsSystem;

import Phase2.OrdersAndNotificationsSystem.repositories.database.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


// to check API documentation go to http://localhost:8080/swagger-ui/index.html#/

@SpringBootApplication
@EnableScheduling
public class OrdersAndNotificationsSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersAndNotificationsSystemApplication.class, args);
    }

}



