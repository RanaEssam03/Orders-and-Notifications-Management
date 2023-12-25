package Phase2.OrdersAndNotificationsSystem.models;

import lombok.Data;

@Data
public class Address {
    String city;
    String region;
    String street;
    Integer apartmentNum;

    public Address(String city, String region, String street, int apartmentNum) {
        this.city=city;
        this.region=region;
        this.street=street;
        this.apartmentNum=apartmentNum;
    }
}
