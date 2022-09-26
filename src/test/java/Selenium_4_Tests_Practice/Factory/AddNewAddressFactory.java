package Selenium_4_Tests_Practice.Factory;

import Selenium_4_Tests_Practice.Models.AddNewAddressData;
import com.github.javafaker.Faker;

public class AddNewAddressFactory {

    private static final Faker faker = new Faker();

    /**
     * Fill a AddNewAddressData object with random data using Faker.
     *
     * @return AddNewAddressData object with the Builder pattern.
     */
    public static AddNewAddressData getAddNewAddressData() {
        return AddNewAddressData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .company(faker.company().name())
                .address1(faker.address().streetAddress())
                .address2(faker.address().secondaryAddress())
                .city(faker.address().city())
                .postcode(faker.address().zipCode())
                .country(faker.address().country())
                .regionState(faker.address().state())
                .defaultAddress(false)
                .build();
    }
}
