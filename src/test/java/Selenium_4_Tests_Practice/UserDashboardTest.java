package Selenium_4_Tests_Practice;

import Selenium_4_Tests_Practice.Pages.LoginPage;
import Selenium_4_Tests_Practice.Pages.UserDashboardPage;
import Selenium_4_Tests_Practice.Utilities.Dashboard.UserDashboardUtility;
import Selenium_4_Tests_Practice.Utilities.LoginPageUtility;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class UserDashboardTest {

    private static final int NO_ACTION_ITEMS = 0;

    public WebDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
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
        assertSoftly(softly -> softly.assertThat(loginPage.getLoginTitle()).describedAs("Returning Customer title is not available").isTrue());
    }

    /**
     * Test to verify that the user is able to load My Account User Dashboard page.
     * Create a New Address Book to the User Dashboard.
     */
    @Test
    @DisplayName("User is able to Add a New Address Book")
    @Tag("Regression")
    void createNewAddressBookDashboard() {
        var loginPage = new LoginPage(driver);
        var loginUtility = new LoginPageUtility(loginPage);
        var userDashboard = new UserDashboardPage(driver);
        var userDashboardUtility = new UserDashboardUtility(userDashboard);
        loginPage.clickLoginLink();
        loginUtility.authenticateWithPublicUserCredentials();
        assertSoftly(softly -> {
            softly.assertThat(userDashboard.getMyAccountTitleDashboard()).describedAs("My Account title on the Dashboard should be available").isTrue();
            softly.assertThat(userDashboard.getTableRowsTotal()).describedAs("The dashboard elements should be shown").isNotEqualTo(NO_ACTION_ITEMS);
        });
        userDashboardUtility.addNewAddressBook();
        assertSoftly(softly -> softly.assertThat(userDashboard.getFirstName()).describedAs("The success message should be shown").isNotBlank());
    }
}
