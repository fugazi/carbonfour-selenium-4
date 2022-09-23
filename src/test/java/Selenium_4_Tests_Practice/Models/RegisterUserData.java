package Selenium_4_Tests_Practice.Models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterUserData {

    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private Boolean newsletterSubscribe;
    private Boolean privacyPolicy;
}

/*
 * This class is used to create a RegisterUserData object with the Builder pattern.
 * Using the Lombok library Builder and Getter annotations.
 */