package Selenium_4_Tests_Practice;

import Selenium_4_Tests_Practice.Pages.RegisterAccountPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class RegisterAccountTest {
    public final String DESCRIPTION_MESSAGE = "Error: The value contained in the input is different from the one entered in the previous step.";
    private static final String REGISTER_ACCOUNT_TITLE = "Register Account";
    private static final String REGISTER_ACCOUNT_SUCCESS_MESSAGE = "Your Account Has Been Created!";
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String telephone;
    private static String password;

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
     * Precondition Scenario: Load the Test Data to perform the Register Account page.
     * Fill the data in the Register Account form.
     */
    @BeforeAll
    public static void loadTestData() {
        firstName = RandomStringUtils.randomAlphabetic(5);
        lastName = RandomStringUtils.randomAlphabetic(5);
        email = RandomStringUtils.randomAlphabetic(8) + "@tesdata.com";
        telephone = RandomStringUtils.randomNumeric(10);
        password = RandomStringUtils.randomAlphabetic(12);
    }

    /**
     * Test to verify that the user is able to load Register Account page.
     */
    @Test
    @Tag("Smoke")
    void testHomeRegisterAccount() {
        registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.clickRegisterLink();
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getRegisterAccountTitle()).isEqualTo(REGISTER_ACCOUNT_TITLE));
    }

    /**
     * First Approach: Test to verify that the user is able to Register an Account.
     * Verify each field with the correct data in the Register Account form.
     */
    @Test
    @Tag("Regression")
    void testRegisterAccount() {
        registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.clickRegisterLink();
        registerAccountPage.enterFirstName(firstName);
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getFirstName())
                .as(DESCRIPTION_MESSAGE).isEqualTo(firstName));
        registerAccountPage.enterLastName(lastName);
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getLastName())
                .as(DESCRIPTION_MESSAGE).isEqualTo(lastName));
        registerAccountPage.enterEmail(email);
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getEmail())
                .as(DESCRIPTION_MESSAGE).isEqualTo(email));
        registerAccountPage.enterTelephone(telephone);
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getTelephone())
                .as(DESCRIPTION_MESSAGE).isEqualTo(telephone));
        registerAccountPage.enterPassword(password);
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getPassword())
                .as(DESCRIPTION_MESSAGE).isEqualTo(password));
        registerAccountPage.enterPasswordConfirm(password);
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getPasswordConfirm())
                .as(DESCRIPTION_MESSAGE).isEqualTo(password));
        registerAccountPage.clickNewsletterRadioButton();
        assertSoftly(softly -> softly.assertThat(registerAccountPage.isNewsletterRadioButtonSelected()).isEqualTo(false));
        registerAccountPage.clickPrivacyPolicyCheckbox();
        assertSoftly(softly -> softly.assertThat(registerAccountPage.isPrivacyPolicyCheckboxSelected()).isEqualTo(false));

        registerAccountPage.clickContinueButton();
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getRegisterAccountTitle()).isEqualTo(REGISTER_ACCOUNT_SUCCESS_MESSAGE));
    }
}
