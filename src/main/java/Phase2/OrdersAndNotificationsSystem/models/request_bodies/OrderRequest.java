package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.Address;
import Phase2.OrdersAndNotificationsSystem.models.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class OrderRequest {
    ArrayList<Integer> productsIDs;
}

