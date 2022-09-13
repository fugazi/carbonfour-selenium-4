package Selenium_4_Tests_Practice;

import Selenium_4_Tests_Practice.Pages.RegisterAccountPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class RegisterAccountTest {
    private static final String REGISTER_ACCOUNT_TITLE = "Register Account";

    public WebDriver driver;
    public RegisterAccountPage registerAccountPage;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        getBaseUrl(driver);
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Test to verify that the user is able to load Register Account page.
     */
    @Test
    @Tag("Smoke")
    public void testHomeRegisterAccount() {
        registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.clickRegisterLink();
        assertSoftly(softly -> {
            softly.assertThat(registerAccountPage.getRegisterAccountTitle()).isEqualTo(REGISTER_ACCOUNT_TITLE);
        });
    }
}
