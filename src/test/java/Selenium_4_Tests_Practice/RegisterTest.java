package Selenium_4_Tests_Practice;

import Selenium_4_Tests_Practice.BaseUtility.BaseUrl;
import Selenium_4_Tests_Practice.Components.FormFieldComponent;
import Selenium_4_Tests_Practice.Pages.RegisterPage;
import Selenium_4_Tests_Practice.Utilities.FormFieldUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class RegisterTest {

    private static final int EXPECTED_ERRORS_TOTAL = 2;
    public WebDriver driver;

    RegisterPage registerPage = new RegisterPage(BaseUrl.driver);

    /**
     * Test to validate the Register Page.
     */
    @ParameterizedTest
    @MethodSource("Source")
    void validateFormFieldErrors(RegisterPage.FormField formField) {
        BaseUrl baseUrl = new BaseUrl(driver);
        baseUrl.setupUrl();
        registerPage.clickContinueButton();
        FormFieldComponent formFieldComponent = registerPage.getFormFieldComponent(formField);
        FormFieldUtility formFieldUtility = FormFieldUtility.getInstance(formFieldComponent);
        formFieldUtility.checkInputText();
    }

    private static Stream<RegisterPage.FormField> Source() {
        return Stream.of(
                        Arrays.stream(RegisterPage.FirstAndLastName.values())
                )
                .flatMap(Function.identity());
    }

    @Test
    void testRegister() {
        BaseUrl baseUrl = new BaseUrl(driver);
        baseUrl.setupUrl();
        registerPage.clickContinueButton();
        // validate error messages
        List<String> errorMessages = registerPage.getFieldErrorMessages();
        assertSoftly(softly -> {
            softly.assertThat(errorMessages.size()).as("The field error message count is not as expected")
                    .isEqualTo(EXPECTED_ERRORS_TOTAL);
            for (String errorMessage : errorMessages) {
                softly.assertThat(errorMessage.contains("Field is required") || errorMessage.contains("The validation is not valid"))
                        .as("The field error message is not as expected. Actual message: " + errorMessage)
                        .isTrue();
            }
        });
        baseUrl.tearDown();
    }
}

