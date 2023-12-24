package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Order;

public class PlacementNotification extends NotificationServices{
    @Override
    protected String createMessage(Order order) {
        return null;
    }
}
