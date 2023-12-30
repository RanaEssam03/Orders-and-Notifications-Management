package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.Notification;
import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationDTO;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationsStatisticsResponse;
import Phase2.OrdersAndNotificationsSystem.services.notifications.*;
import Phase2.OrdersAndNotificationsSystem.services.notifications.channel.MessageChannel;
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
    @Autowired
    NotificationStatisticServices notificationStatisticServices;

    public NotificationController(ShipmentNotificationServices shipmentNotificationServices, CancellationNotificationServices cancellationNotificationServices,
                                  PlacementNotificationServices placementNotificationServices) {
        this.notificationServices.add(shipmentNotificationServices);
        this.notificationServices.add(placementNotificationServices);
        this.notificationServices.add(cancellationNotificationServices);
    }

    @GetMapping("/all")
    ArrayList<NotificationDTO> getAll(){
        return notificationServices.get(0).getNotifications();
    }

    @GetMapping("/most-sent-template")
    String getMostSentTemplate(){
        return notificationStatisticServices.getMostSentTemplate();
    }

    @GetMapping("/most-notified-user")
    String getMostNotifiedUser() throws GeneralException {
        return notificationStatisticServices.getMostNotifiedUser();
    }
    @GetMapping("/get-all-statistics")
    NotificationsStatisticsResponse getStatistics() throws GeneralException {
        String mostSentTemplate = notificationStatisticServices.getMostSentTemplate();
        String notifiedUser = notificationStatisticServices.getMostNotifiedUser();
        return new NotificationsStatisticsResponse(mostSentTemplate, notifiedUser);
    }

}
