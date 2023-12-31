package Phase2.OrdersAndNotificationsSystem.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class Category {
    /**
     * The name of the product category.
     */
    @Schema(example = "Electronics")
    String name;

    /**
     * The unique identifier (ID) of the product category.
     */
    @Schema(example = "12")
    Integer id;

    /**
     * A static counter used to assign unique IDs to categories.
     */
    @JsonIgnore
    static int cnt = 0;

    /**
     * Constructs a Category with the specified name, assigning a unique ID.
     *
     * @param name The name of the product category.
     */
    public Category(String name) {
        this.name = name;
        id = cnt++;
    }
}
