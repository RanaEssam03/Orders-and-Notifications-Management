package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationDTO;
import Phase2.OrdersAndNotificationsSystem.services.notifications.CancellationNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.NotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.PlacementNotificationServices;
import Phase2.OrdersAndNotificationsSystem.services.notifications.ShipmentNotificationServices;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

import static Phase2.OrdersAndNotificationsSystem.repositories.database.Data.messagesQueue;

@RestController
@RequestMapping("api/notification")
public class NotificationController {

    ArrayList<NotificationServices> notificationServices = new ArrayList<>();

    public NotificationController(ShipmentNotificationServices shipmentNotificationServices, CancellationNotificationServices cancellationNotificationServices,
                                  PlacementNotificationServices placementNotificationServices) {
        this.notificationServices.add(shipmentNotificationServices);
        this.notificationServices.add(placementNotificationServices);
        this.notificationServices.add(cancellationNotificationServices);
    }

    @GetMapping("/all")
    ArrayList<NotificationDTO> getAll(){
        ArrayList<NotificationDTO> arrayList = new ArrayList<>();
        for (NotificationServices notSer: notificationServices){
            ArrayList<NotificationDTO> dtoArrayList = notSer.getNotifications();
            arrayList.addAll(dtoArrayList);
        }
        return arrayList;
    }

    @GetMapping("/statistics")
    Optional<String> getStatistics(){
        Integer mostSentNotificationTemplateCount = -1;
        String mostSentNotificationTemplate = null;
        for (NotificationServices notSer: notificationServices){
            Integer cnt = notSer.getCount();
            if (cnt > mostSentNotificationTemplateCount){
                mostSentNotificationTemplateCount = cnt;
                mostSentNotificationTemplate = notSer.toString();
            }
        }
        if (mostSentNotificationTemplate == null){
            return Optional.empty();
        }
        return mostSentNotificationTemplate.describeConstable();
    }
}
