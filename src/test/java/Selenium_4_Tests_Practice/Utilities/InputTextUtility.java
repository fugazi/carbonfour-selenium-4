package Selenium_4_Tests_Practice.Utilities;

import Selenium_4_Tests_Practice.Components.InputTextComponent;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;

public class InputTextUtility extends FormFieldUtility {

    public final String ERROR_INPUT_FIRST_NAME_MESSAGE = "First Name must be between 1 and 32 characters!";
    public final String ERROR_INPUT_LAST_NAME_MESSAGE = "Last Name must be between 1 and 32 characters!";

    private final InputTextComponent component;

    /**
     * Constructor for InputTextUtility class.
     * @param component InputTextComponent
     */
    public InputTextUtility(InputTextComponent component) {
        this.component = component;
    }

    /**
     * Check the input text field.
     * 'First Name and Last name' fields
     * Do assertions on the input text field.
     */
    @Override
    public void checkInputText() {
        SoftAssertions report = new SoftAssertions();
        if (component.getMinChar() > 1) {
            component.addInputText(RandomStringUtils.randomAlphabetic(component.getMaxChar() - 1));
            report.assertThat(ERROR_INPUT_FIRST_NAME_MESSAGE).isEqualTo(component.getErrorMessage());
        } else if (component.getMinChar() != 0) {
            component.addInputText(RandomStringUtils.randomAlphabetic(component.getMaxChar() + 1));
            report.assertThat(ERROR_INPUT_FIRST_NAME_MESSAGE).isEqualTo(component.getErrorMessage());
        } else if (component.getMaxChar() > 1) {
            component.addInputText(RandomStringUtils.randomAlphabetic(component.getMinChar() - 1));
            report.assertThat(ERROR_INPUT_LAST_NAME_MESSAGE).isEqualTo(component.getErrorMessage());
        } else if (component.getMaxChar() != 0) {
            component.addInputText(RandomStringUtils.randomAlphabetic(component.getMinChar() + 1));
            report.assertThat(ERROR_INPUT_LAST_NAME_MESSAGE).isEqualTo(component.getErrorMessage());
        }
        report.assertAll();
    }
}
