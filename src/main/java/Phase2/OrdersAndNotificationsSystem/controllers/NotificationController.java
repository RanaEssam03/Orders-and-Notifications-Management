package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.messagesQueue;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
    @GetMapping("/all")
    ArrayList<Notification> getAll(){
        return messagesQueue;
    }
}
