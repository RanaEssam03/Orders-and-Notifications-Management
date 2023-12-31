package Phase2.OrdersAndNotificationsSystem.controllers;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationDTO;
import Phase2.OrdersAndNotificationsSystem.models.response_bodies.NotificationsStatisticsResponse;
import Phase2.OrdersAndNotificationsSystem.services.notifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Controller class for managing notification-related operations.
 * Provides endpoints for retrieving notifications and notification statistics.
 */
@RestController
@RequestMapping("api/notification")
public class NotificationController {

    ArrayList<NotificationServices> notificationServices = new ArrayList<>();

    @Autowired
    NotificationStatisticServices notificationStatisticServices;

    /**
     * Constructor for the NotificationController class.
     *
     * @param shipmentNotificationServices     The service for managing shipment notifications.
     * @param cancellationNotificationServices The service for managing cancellation notifications.
     * @param placementNotificationServices    The service for managing placement notifications.
     */
    public NotificationController(ShipmentNotificationServices shipmentNotificationServices,
                                  CancellationNotificationServices cancellationNotificationServices,
                                  PlacementNotificationServices placementNotificationServices) {
        this.notificationServices.add(shipmentNotificationServices);
        this.notificationServices.add(placementNotificationServices);
        this.notificationServices.add(cancellationNotificationServices);
    }

    /**
     * Retrieves all notifications.
     *
     * @return An ArrayList of NotificationDTO representing all notifications.
     */
    @GetMapping("/all")
    ArrayList<NotificationDTO> getAll() {
        return notificationServices.get(0).getNotifications();
    }

    /**
     * Retrieves the template of the most sent notification.
     *
     * @return A String representing the template of the most sent notification.
     */
    @GetMapping("/most-sent-template")
    String getMostSentTemplate() {
        return notificationStatisticServices.getMostSentTemplate();
    }

    /**
     * Retrieves the username of the user who has been notified the most.
     *
     * @return A String representing the username of the most notified user.
     * @throws GeneralException If there is an issue with retrieving the most notified user.
     */
    @GetMapping("/most-notified-user")
    String getMostNotifiedUser() throws GeneralException {
        return notificationStatisticServices.getMostNotifiedUser();
    }

    /**
     * Retrieves notification statistics including the most sent template and the most notified user.
     *
     * @return A NotificationsStatisticsResponse containing the most sent template and the most notified user.
     * @throws GeneralException If there is an issue with retrieving notification statistics.
     */
    @GetMapping("/get-all-statistics")
    NotificationsStatisticsResponse getStatistics() throws GeneralException {
        String mostSentTemplate = notificationStatisticServices.getMostSentTemplate();
        String notifiedUser = notificationStatisticServices.getMostNotifiedUser();
        return new NotificationsStatisticsResponse(mostSentTemplate, notifiedUser);
    }
}
