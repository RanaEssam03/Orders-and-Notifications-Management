package Phase2.OrdersAndNotificationsSystem.services.notifications;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import Phase2.OrdersAndNotificationsSystem.repositories.NotificationsRepository;
import Phase2.OrdersAndNotificationsSystem.repositories.database.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public String getMostNotifiedUser() throws GeneralException {

        int smsCount = 0;
        if(!Data.smsStatistics.isEmpty())
            smsCount = Collections.max(Data.smsStatistics.entrySet(), Map.Entry.comparingByValue()).getValue();
        int emailCount = 0;
        if(!Data.emailStatistics.isEmpty())
            emailCount = Collections.max(Data.emailStatistics.entrySet(), Map.Entry.comparingByValue()).getValue();

        if(smsCount == 0 && emailCount == 0)
           throw new GeneralException(HttpStatus.BAD_REQUEST, "No notifications sent yet");

        if (smsCount > emailCount)
            return Collections.max(Data.smsStatistics.entrySet(), Map.Entry.comparingByValue()).getKey();
        else
            return Collections.max(Data.emailStatistics.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
