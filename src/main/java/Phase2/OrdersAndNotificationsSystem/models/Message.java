package Phase2.OrdersAndNotificationsSystem.models;

import io.swagger.v3.oas.annotations.media.Schema;
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
