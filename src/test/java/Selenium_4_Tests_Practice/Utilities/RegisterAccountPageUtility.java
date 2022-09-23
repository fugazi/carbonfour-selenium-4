package Selenium_4_Tests_Practice.Utilities;

import Selenium_4_Tests_Practice.Factory.RegisterUserFactory;
import Selenium_4_Tests_Practice.Models.RegisterUserData;
import Selenium_4_Tests_Practice.Pages.RegisterAccountPage;
import org.openqa.selenium.WebDriver;

public class RegisterAccountPageUtility {

    private final RegisterAccountPage registerAccountPage;
    private final RegisterUserData registerUserData;

    /**
     * Constructor for the RegisterAccountPageUtility class.
     */
    public RegisterAccountPageUtility(WebDriver driver) {
        this.registerAccountPage = new RegisterAccountPage(driver);
        this.registerUserData = RegisterUserFactory.getRegisterUserData();
    }

    /**
     * This RegisterUserFactory object with the Builder pattern.
     * Fill the data on the Register Account form.
     */
    public void fillRegisterAccountForm() {
        registerAccountPage.enterFirstName(registerUserData.getFirstName());
        registerAccountPage.enterLastName(registerUserData.getLastName());
        registerAccountPage.enterEmail(registerUserData.getEmail());
        registerAccountPage.enterTelephone(registerUserData.getTelephone());
        registerAccountPage.enterPassword(registerUserData.getPassword());
        registerAccountPage.enterPasswordConfirm(registerUserData.getPassword());
        registerAccountPage.clickNewsletterRadioButton();
        registerAccountPage.clickPrivacyPolicyCheckbox();
    }
}
