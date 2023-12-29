package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.order.Order;

public class PlacementNotification extends NotificationServices{
    String message = "Dear {x},\nYour order of ";
    @Override
    protected String createMessage(Order order) {
        return null;
    }
}
