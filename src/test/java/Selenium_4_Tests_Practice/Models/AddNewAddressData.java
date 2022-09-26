package Selenium_4_Tests_Practice.Models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddNewAddressData {

    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String postcode;
    private String country;
    private String regionState;
    private Boolean defaultAddress;
}

/*
 * This class is used to create a AddNewAddressData object with the Builder pattern.
 * Using the Lombok library Builder and Getter annotations.
 */