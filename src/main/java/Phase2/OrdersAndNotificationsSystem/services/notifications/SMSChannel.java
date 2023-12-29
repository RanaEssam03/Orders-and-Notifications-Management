package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.ShipmentNotificationServices;

import java.util.Queue;

public class SMSChannel implements MessageChannel{
    Queue<ShipmentNotificationServices> messages;
    @Override
    public void sendMessage(Notification notification){

    }
    @Override
    public boolean verifyContact(String contact){
        return true;
    }

}
