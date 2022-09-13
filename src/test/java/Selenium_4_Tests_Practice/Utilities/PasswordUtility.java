package Selenium_4_Tests_Practice.Utilities;

import Selenium_4_Tests_Practice.Components.PasswordComponent;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;

public class PasswordUtility extends FormFieldUtility {

    public final String ERROR_PASSWORD_MESSAGE = "Password must be between 4 and 20 characters!";

    private final PasswordComponent component;

    /**
     * Constructor for PasswordUtility class.
     *
     * @param component PasswordComponent
     */
    public PasswordUtility(PasswordComponent component) {
        this.component = component;

    }

    /**
     * Check the password field.
     * 'Password' field
     * Do assertions on the password field.
     */
    @Override
    public void checkInputText() {
        SoftAssertions report = new SoftAssertions();
        component.addPasswordText(RandomStringUtils.randomAlphanumeric(component.getMaxChar()));
        report.assertThat(ERROR_PASSWORD_MESSAGE).isEqualTo(component.getPasswordErrorMessage());
        report.assertAll();
    }

//    /**
//     * Check the password text.
//     * @param passwordText password text
//     * @return true if the password text is valid
//     */
//    public static boolean checkPasswordText(String passwordText) {
//        return passwordText.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
//    }
}
