package Phase2.OrdersAndNotificationsSystem.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Address {
    /**
     * The city of the address.
     */
    @Schema(example = "Dokki")
    String city;

    /**
     * The region of the address.
     */
    @Schema(example = "Giza")
    String region;

    /**
     * The street of the address.
     */
    @Schema(example = "Abdelmenam Reyad")
    String street;

    /**
     * The apartment number of the address.
     */
    @Schema(example = "601")
    Integer apartmentNum;

    /**
     * Constructs an Address with the specified parameters.
     *
     * @param city         The city of the address.
     * @param region       The region of the address.
     * @param street       The street of the address.
     * @param apartmentNum The apartment number of the address.
     */
    public Address(String city, String region, String street, int apartmentNum) {
        this.city=city;
        this.region=region;
        this.street=street;
        this.apartmentNum=apartmentNum;
    }
}
