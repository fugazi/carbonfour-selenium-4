package Selenium_4_Tests_Practice;

import Selenium_4_Tests_Practice.Components.FormFieldComponent;
import Selenium_4_Tests_Practice.Pages.RegisterPage;
import Selenium_4_Tests_Practice.Utilities.FormFieldUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class RegisterTest {

    private static final int EXPECTED_ERRORS_TOTAL = 5;
    private static final String REGISTER_ACCOUNT_TITLE = "Register Account";
    private static final String FIRST_NAME_ERROR_MESSAGE = "First Name must be between 1 and 32 characters!";
    private static final String LAST_NAME_ERROR_MESSAGE = "Last Name must be between 1 and 32 characters!";
    private static final String EMAIL_ERROR_MESSAGE = "E-Mail Address does not appear to be valid!";
    private static final String TELEPHONE_ERROR_MESSAGE = "Telephone must be between 3 and 32 characters!";
    private static final String PASSWORD_ERROR_MESSAGE = "Password must be between 4 and 20 characters!";

    public WebDriver driver;
    public RegisterPage registerPage;

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
    public void testHomeRegister() {
        registerPage = new RegisterPage(driver);
        registerPage.clickMyAccountLink();
        assertSoftly(softly -> {
            softly.assertThat(registerPage.getRegisterAccountTitle()).isEqualTo(REGISTER_ACCOUNT_TITLE);
        });
    }

    /**
     * Verify the error messages count and red alert text without filling the Register Account Form.
     */
    @Test
    @Tag("Regression")
    void testErrorFieldMessages() {
        registerPage = new RegisterPage(driver);
        registerPage.clickMyAccountLink();
        registerPage.clickContinueButton();
        // validate error messages
        List<String> errorMessages = registerPage.getFieldErrorMessages();
        assertSoftly(softly -> {
            softly.assertThat(errorMessages.size()).as("The field error message count is not as expected")
                    .isEqualTo(EXPECTED_ERRORS_TOTAL);
            for (String errorMessage : errorMessages) {
                softly.assertThat(errorMessage.contains(FIRST_NAME_ERROR_MESSAGE) || errorMessage.contains(LAST_NAME_ERROR_MESSAGE)
                                || errorMessage.contains(EMAIL_ERROR_MESSAGE) || errorMessage.contains(TELEPHONE_ERROR_MESSAGE) || errorMessage.contains(PASSWORD_ERROR_MESSAGE))
                        .as("The field error message is not as expected. Actual message: " + errorMessage)
                        .isTrue();
            }
        });
    }

    /**
     * Test to validate the Register Page.
     */
    @ParameterizedTest
    @MethodSource("Source")
    @Tag("Regression")
    void validateFormFieldErrors(RegisterPage.FormField formField) {
        registerPage = new RegisterPage(driver);
        registerPage.clickMyAccountLink();
        registerPage.clickContinueButton();
        FormFieldComponent formFieldComponent = registerPage.getFormFieldComponent(formField);
        FormFieldUtility formFieldUtility = FormFieldUtility.getInstance(formFieldComponent);
        formFieldUtility.checkInputText();
    }

    private static Stream<RegisterPage.FormField> Source() {
        return Stream.of(
                        Arrays.stream(RegisterPage.FirstAndLastName.values()),
                        Arrays.stream(RegisterPage.Email.values()),
                        Arrays.stream(RegisterPage.Telephone.values())
                )
                .flatMap(Function.identity());
    }
}

