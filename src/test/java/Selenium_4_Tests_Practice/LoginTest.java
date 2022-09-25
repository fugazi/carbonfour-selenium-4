package Selenium_4_Tests_Practice;

import Selenium_4_Tests_Practice.Pages.LoginPage;
import Selenium_4_Tests_Practice.Utilities.LoginPageUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class LoginTest {

    private static final int NO_ACTION_ITEMS = 0;

    public WebDriver driver;

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
     * Test to verify that the user is able to load Login page.
     */
    @Test
    @DisplayName("Login page is loaded successfully")
    @Tag("Smoke")
    void verifyUserIsAbleToLoadLoginPage() {
        var loginPage = new LoginPage(driver);
        loginPage.clickLoginLink();
        assertSoftly(softly -> softly.assertThat(loginPage.getLoginTitle())
                .describedAs("Returning Customer title is not available")
                .isTrue());
    }

    /**
     * Test to verify the login with valid credentials: ADMIN_USER
     */
    @Test
    @DisplayName("Login with valid credentials: ADMIN_USER")
    @Tag("Regression")
    void loginWithAdminUser() {
        var loginPage = new LoginPage(driver);
        var loginUtility = new LoginPageUtility(loginPage);
        loginPage.clickLoginLink();
        loginUtility.authenticateWithAdminCredentials();
        assertSoftly(softly -> softly.assertThat(loginPage.verifyUserLoginDashboard())
                .describedAs("The user is not logged in as ADMIN_USER")
                .isTrue());
        assertSoftly(softly -> softly.assertThat(loginPage.getTableRowsTotal())
                .describedAs("The action elements should be shown")
                .isGreaterThan(NO_ACTION_ITEMS));
    }

    /**
     * Test to verify the login with invalid credentials: NO_PERMISSIONS_USER
     */
    @Test
    @DisplayName("Login with invalid credentials: NO_PERMISSIONS_USER")
    @Tag("Regression")
    void loginWithNoPermissionsUser() {
        var loginPage = new LoginPage(driver);
        var loginUtility = new LoginPageUtility(loginPage);
        loginPage.clickLoginLink();
        loginUtility.authenticateWithNoPermissionsCredentials();
        assertSoftly(softly -> softly.assertThat(loginPage.verifyLoginErrorMessage())
                .describedAs("The user is not able to authenticate in the form")
                .isTrue());
    }

    /**
     * Test to verify the login with valid credentials: PUBLIC_USER
     */
    @Test
    @DisplayName("Login with valid credentials: PUBLIC_USER")
    @Tag("Regression")
    void loginWithPublicUser() {
        var loginPage = new LoginPage(driver);
        var loginUtility = new LoginPageUtility(loginPage);
        loginPage.clickLoginLink();
        loginUtility.authenticateWithPublicUserCredentials();
        assertSoftly(softly -> softly.assertThat(loginPage.verifyUserLoginDashboard())
                .describedAs("The user is not logged in as PUBLIC_USER")
                .isTrue());
        assertSoftly(softly -> softly.assertThat(loginPage.getTableRowsTotal())
                .describedAs("The action elements should be shown")
                .isGreaterThan(NO_ACTION_ITEMS));
    }
}
