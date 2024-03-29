package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class CompoundOrderRequest {

    // This is the ID of the user who is making the order request
    Map<String,Integer > otherOrders = new HashMap();

    ArrayList<Integer> productsIDs = new ArrayList<>();

}
