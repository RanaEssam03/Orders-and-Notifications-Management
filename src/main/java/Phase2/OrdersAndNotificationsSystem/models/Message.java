package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

@Data
public class Message {
    Integer id;
    String content;
    String contact;

    public Message(String content, String contact) {
        this.content = content;
        this.contact = contact;
    }
}
