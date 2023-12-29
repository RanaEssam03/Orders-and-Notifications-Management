package Phase2.OrdersAndNotificationsSystem.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Address {
    @Schema(example = "Dokki")
    String city;
    @Schema(example = "Giza")
    String region;
    @Schema(example = "Abdelmenam Reyad")
    String street;
    @Schema(example = "601")
    Integer apartmentNum;

    public Address(String city, String region, String street, int apartmentNum) {
        this.city=city;
        this.region=region;
        this.street=street;
        this.apartmentNum=apartmentNum;
    }
}
