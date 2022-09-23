package Selenium_4_Tests_Practice.Factory;

import Selenium_4_Tests_Practice.Models.RegisterUserData;
import com.github.javafaker.Faker;

public class RegisterUserFactory {

    private static final Faker faker = new Faker();

    /**
     * Fill a RegisterUserData object with random data using Faker.
     * @return RegisterUserData object with the Builder pattern.
     */

    public static RegisterUserData getRegisterUserData() {
        return RegisterUserData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .telephone(faker.phoneNumber().phoneNumber())
                .password(faker.internet().password())
                .newsletterSubscribe(false)
                .privacyPolicy(true)
                .build();
    }
}
