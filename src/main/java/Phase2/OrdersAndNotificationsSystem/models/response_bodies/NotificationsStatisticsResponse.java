package Phase2.OrdersAndNotificationsSystem.models.response_bodies;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationsStatisticsResponse {
    /**
     * The template that has been most frequently sent in notifications.
     */
    String mostSentTemplate;

    /**
     * The user who has been most frequently notified.
     */
    String mostNotifiedUser;

    /**
     * Constructs a NotificationsStatisticsResponse with the specified most sent template and most notified user.
     *
     * @param mostSentTemplate The template that has been most frequently sent in notifications.
     * @param mostNotifiedUser The user who has been most frequently notified.
     */
    public NotificationsStatisticsResponse(String mostSentTemplate, String mostNotifiedUser) {
        this.mostSentTemplate = mostSentTemplate;
        this.mostNotifiedUser = mostNotifiedUser;
    }
}
