package Phase2.OrdersAndNotificationsSystem.services.notifications.channel;

import java.text.SimpleDateFormat;
import java.util.Date;
import Phase2.OrdersAndNotificationsSystem.repositories.database.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Scheduled(fixedDelay = 60*1000, initialDelay = 60*1000)
    public void fixedDelaySch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Delay scheduler:: " + strDate);
        if(!Data.messagesQueue.isEmpty()){
            String notification = Data.messagesQueue.get(0).getContent();
            System.out.println(notification);
            Data.messagesQueue.remove(0);
        }
    }
}