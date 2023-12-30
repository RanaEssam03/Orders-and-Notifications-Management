package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.repositories.database.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class NotificationStatisticsServicesImpl implements NotificationStatisticServices{
    @Autowired
    NotificationsRepository notificationsRepository;

    @Override
    public String getMostSentTemplate() {
        int shipmentCount = notificationsRepository.getShipmentCounter();
        int cancellationCount = notificationsRepository.getCancellationCounter();
        int placementCount = notificationsRepository.getConfirmationCounter();
        int max = Math.max(shipmentCount, Math.max(cancellationCount, placementCount));
        if (max == shipmentCount) return "Shipment Template";
        if (max == placementCount) return "Placement Template";
        else return "Cancellation Template";
    }

    @Override
    public String getMostNotifiedUser() {
        int smsCount = Collections.max(Data.smsStatistics.entrySet(), Map.Entry.comparingByValue()).getValue();
        int emailCount = Collections.max(Data.emailStatistics.entrySet(), Map.Entry.comparingByValue()).getValue();
        if (smsCount > emailCount)
            return Collections.max(Data.smsStatistics.entrySet(), Map.Entry.comparingByValue()).getKey();
        else
            return Collections.max(Data.emailStatistics.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
