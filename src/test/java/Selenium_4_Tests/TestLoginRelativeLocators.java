package Selenium_4_Tests;

import Selenium_4_Tests.Pages.LoginPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class TestLoginRelativeLocators {

    private static final String CREDENTIALS_TEXT = "Username: admin Password: admin123";
    private static final String USERNAME_TEXT = "Username";

    public WebDriver driver;

    /**
     * Check if the Login Logo is displayed. The happy way ever < Selenium 4.0.
     */
    @Test
    @Tag("Smoke")
    void isLoginLogoDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        assertSoftly(softly -> softly.assertThat(loginPage.isLoginLogoDisplayed())
                .describedAs("OrangeHRM is loaded. Login logo is displayed")
                .isTrue());
        loginPage.tearDown();
    }

    /**
     * Get the Login Panel title. The happy way ever < Selenium 4.0.
     */
    @Test
    @Tag("Smoke")
    void getLoginPanelTitle() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        assertSoftly(softly -> softly.assertThat(loginPage.getLoginPanelTitle()).isEqualTo("LOGIN Panel"));
        loginPage.tearDown();
    }

    /**
     * Get the credentials text using Relative Locators Selenium 4.0.
     * Above of the Login Panel.
     */

    @Test
    @Tag("Regression")
    void testRelativeLocatorsAbove() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        assertSoftly(softly -> softly.assertThat(loginPage.getCredentialsTextRelativeLocatorsAbove()).isEqualTo(CREDENTIALS_TEXT));
        loginPage.tearDown();
    }

    /**
     * Get the username text using Relative Locators Selenium 4.0.
     * Below of the Login Panel.
     */
    @Test
    @Tag("Regression")
    void testRelativeLocatorsBelow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        assertSoftly(softly -> softly.assertThat(loginPage.getUsernameTextRelativeLocatorsBelow()).isEqualTo(USERNAME_TEXT));
        loginPage.tearDown();
    }

    /**
     * Get the login image using Relative Locators Selenium 4.0.
     * Left of the Login Panel.
     */
    @Test
    @Tag("Regression")
    void testRelativeLocatorsImage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        assertSoftly(softly -> softly.assertThat(loginPage.getLoginImageRelativeLocatorsLeft())
                .describedAs("The Login image should be available on the left of the Login Panel")
                .isTrue());
        loginPage.tearDown();
    }

    /**
     * Get the Login Submit button using Relative Locators Selenium 4.0.
     * Right of the Login Image.
     */
    @Test
    @Tag("Regression")
    void testRelativeLocatorsSubmitButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        assertSoftly(softly -> softly.assertThat(loginPage.getLoginSubmitButtonRelativeLocatorsRight())
                .describedAs("The Login Submit button should be available on the right of the Login Image")
                .isTrue());
        loginPage.tearDown();
    }

    /**
     * Click on the Forgot Password link using Relative Locators Selenium 4.0.
     * Near of the Login Submit button.
     */
    @Test
    @Tag("Regression")
    void testRelativeLocatorsForgotPasswordLink() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        loginPage.clickForgotPasswordRelativeLocatorsNear();
        assertSoftly(softly -> softly.assertThat(loginPage.clickForgotPasswordRelativeLocatorsNear())
                .describedAs("The Forgot Password link should be available near the Login Submit button")
                .exists());
        loginPage.tearDown();
    }

    /**
     * Get a list of social images attributes of all the elements using Relative Locators Selenium 4.0.
     * Near of the footer.
     */
    @Test
    @Tag("Regression")
    void testRelativeLocatorsSocialImages() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUp();
        assertSoftly(softly -> softly.assertThat(loginPage.getSocialImagesRelativeLocatorsNear())
                .describedAs("The social images should be available near the footer")
                .isNotNull());
        loginPage.tearDown();
    }
}
