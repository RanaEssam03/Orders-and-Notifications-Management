package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationDTO;
import Phase2.OrdersAndNotificationsSystem.services.notifications.NotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.ShipmentNotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.messagesQueue;

@RestController
@RequestMapping("api/notification")
public class NotificationController {


    NotificationServices notificationServices;

    public NotificationController(ShipmentNotificationServices notificationServices) {
        this.notificationServices = notificationServices;
    }

    @GetMapping("/all")
    ArrayList<NotificationDTO> getAll(){
        return notificationServices.getNotifications();
    }
}
