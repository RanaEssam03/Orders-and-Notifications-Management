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
        String returnString = "The most used template is %s in the %s notification";
        Map<String, Integer> shipmentCount = notificationsRepository.getShipmentCounter();
        Map<String, Integer> cancellationCount = notificationsRepository.getCancellationCounter();
        Map<String, Integer> placementCount = notificationsRepository.getConfirmationCounter();
        int shipment = Collections.max(notificationsRepository.getShipmentCounter().entrySet(), Map.Entry.comparingByValue()).getValue();
        int cancellation = Collections.max(notificationsRepository.getCancellationCounter().entrySet(), Map.Entry.comparingByValue()).getValue();
        int placement = Collections.max(notificationsRepository.getConfirmationCounter().entrySet(), Map.Entry.comparingByValue()).getValue();
        int max = Math.max(shipment, Math.max(cancellation, placement));
        if (max == shipment) {
            for (Map.Entry<String,Integer> entry : shipmentCount.entrySet()){
                if (entry.getValue() == shipment){
                    return String.format(returnString, entry.getKey(), "Shipment");
                }
            }
        }
        if (max == placement) {
            for (Map.Entry<String,Integer> entry : placementCount.entrySet()){
                if (entry.getValue() == placement){
                    return String.format(returnString, entry.getKey(), "Placement");
                }
            }
        }
        else {
            for (Map.Entry<String, Integer> entry : cancellationCount.entrySet()) {
                if (entry.getValue() == cancellation) {
                    return String.format(returnString, entry.getKey(), "Cancellation");
                }
            }
        }
        return null;
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
