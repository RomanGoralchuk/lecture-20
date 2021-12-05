package by.itacademy.javaenterprise.goralchuk.entity.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private Long zipCode;
    private String city;
    private String street;
    private int houseNumber;
    private int apartmentNumber;
}
