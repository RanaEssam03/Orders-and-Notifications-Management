package Phase2.OrdersAndNotificationsSystem.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class Category {
    String name;
    Integer id;

    @JsonIgnore
    static int cnt=0;

    public Category(String name) {
        this.name = name;
        id = cnt++;
    }
    public Category(){
        id = cnt++;

    };

}
