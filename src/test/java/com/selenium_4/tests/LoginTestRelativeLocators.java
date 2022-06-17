package com.selenium_4.tests;

import com.selenium_4.tests.Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class LoginTestRelativeLocators {

    private static final String CREDENTIALS_TEXT = "Username: admin Password: admin123";
    private static final String USERNAME_TEXT = "Username";

    public WebDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Check if the Login Logo is displayed. The happy way ever < Selenium 4.0.
     */
    @Test
    void isLoginLogoDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(loginPage.isLoginLogoDisplayed())
                    .describedAs("OrangeHRM is loaded. Login logo is displayed")
                    .isTrue();
        });
    }

    /**
     * Get the Login Panel title. The happy way ever < Selenium 4.0.
     */
    @Test
    void getLoginPanelTitle() {
        LoginPage loginPage = new LoginPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(loginPage.getLoginPanelTitle()).isEqualTo("LOGIN Panel");
        });
    }

    /**
     * Get the credentials text using Relative Locators Selenium 4.0.
     * Above of the Login Panel.
     */

    @Test
    void testRelativeLocatorsAbove() {
        LoginPage loginPage = new LoginPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(loginPage.getCredentialsTextRelativeLocatorsAbove()).isEqualTo(CREDENTIALS_TEXT);
        });
    }

    /**
     * Get the username text using Relative Locators Selenium 4.0.
     * Below of the Login Panel.
     */
    @Test
    void testRelativeLocatorsBelow() {
        LoginPage loginPage = new LoginPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(loginPage.getUsernameTextRelativeLocatorsBelow()).isEqualTo(USERNAME_TEXT);
        });
    }

    /**
     * Get the login image using Relative Locators Selenium 4.0.
     * Left of the Login Panel.
     */
    @Test
    void testRelativeLocatorsImage() {
        LoginPage loginPage = new LoginPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(loginPage.getLoginImageRelativeLocatorsLeft())
                    .describedAs("The Login image should be available on the left of the Login Panel")
                    .isTrue();
        });
    }

    /**
     * Get the Login Submit button using Relative Locators Selenium 4.0.
     * Right of the Login Image.
     */
    @Test
    void testRelativeLocatorsSubmitButton() {
        LoginPage loginPage = new LoginPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(loginPage.getLoginSubmitButtonRelativeLocatorsRight())
                    .describedAs("The Login Submit button should be available on the right of the Login Image")
                    .isTrue();
        });
    }

    /**
     * Click on the Forgot Password link using Relative Locators Selenium 4.0.
     * Near of the Login Submit button.
     */
    @Test
    void testRelativeLocatorsForgotPasswordLink() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordRelativeLocatorsNear();
        assertSoftly(softly -> {
            softly.assertThat(loginPage.clickForgotPasswordRelativeLocatorsNear())
                    .describedAs("The Forgot Password link should be available near the Login Submit button")
                    .exists();
        });
    }

    /**
     * Get a list of social images attributes of all the elements using Relative Locators Selenium 4.0.
     * Near of the footer.
     */
    @Test
    void testRelativeLocatorsSocialImages() {
        LoginPage loginPage = new LoginPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(loginPage.getSocialImagesRelativeLocatorsNear())
                    .describedAs("The social images should be available near the footer")
                    .isNotNull();
        });
    }
}
