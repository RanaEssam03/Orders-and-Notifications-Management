package Phase2.OrdersAndNotificationsSystem.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class Category {
    @Schema(example = "Electronics")
    String name;
    @Schema(example = "12")
    Integer id;

    @JsonIgnore
    static int cnt=0;

    public Category(String name) {
        this.name = name;
        id = cnt++;
    }


}
