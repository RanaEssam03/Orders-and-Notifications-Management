package Phase2.OrdersAndNotificationsSystem.models.request_bodies;

import Phase2.OrdersAndNotificationsSystem.models.Account;
import Phase2.OrdersAndNotificationsSystem.models.Address;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CompoundOrderRequest {

    Integer accountID;

    ArrayList<SimpleOrder> orders = new ArrayList<>();

    ArrayList<Integer> products = new ArrayList<>();

}
