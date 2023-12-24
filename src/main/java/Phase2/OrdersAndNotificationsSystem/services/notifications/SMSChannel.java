package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.Message;

import java.util.Queue;

public class SMSChannel implements MessageChannel{
    Queue<Message> messages;
    @Override
    public void sendMessage(Message message){

    }
    @Override
    public boolean verifyContact(String contact){
        return true;
    }

}
