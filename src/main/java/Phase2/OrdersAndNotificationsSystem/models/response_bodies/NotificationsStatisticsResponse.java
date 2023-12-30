package Phase2.OrdersAndNotificationsSystem.models.response_bodies;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationsStatisticsResponse {
    String mostSentTemplate;
    String mostNotifiedUser;
    public NotificationsStatisticsResponse(String mostSentTemplate, String mostNotifiedUser) {
        this.mostSentTemplate = mostSentTemplate;
        this.mostNotifiedUser = mostNotifiedUser;
    }
}
