package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;

public interface NotificationStatisticServices {
    String getMostSentTemplate();
    String getMostNotifiedUser() throws GeneralException;
}
