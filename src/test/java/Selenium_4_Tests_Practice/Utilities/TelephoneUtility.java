package Selenium_4_Tests_Practice.Utilities;

import Selenium_4_Tests_Practice.Components.TelephoneComponent;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;

public class TelephoneUtility extends FormFieldUtility {

        public final String ERROR_TELEPHONE_MESSAGE = "Telephone must be between 3 and 32 characters!";
        public final String DESCRIPTION_MESSAGE = "Enter valid phone number with country code!";


    private final TelephoneComponent component;

        /**
        * Constructor for TelephoneUtility class.
        * @param component TelephoneComponent
        */
        public TelephoneUtility(TelephoneComponent component) {
            this.component = component;
        }

        /**
        * Check the telephone field.
        * 'Telephone' field
        * Do assertions on the telephone field.
        */
        @Override
        public void checkInputText() {
            SoftAssertions report = new SoftAssertions();
            for (int i = 0; i < component.getMaxChar(); i++) {
                component.addTelephoneText(RandomStringUtils.randomNumeric(i));
                report.assertThat(ERROR_TELEPHONE_MESSAGE)
                        .describedAs(DESCRIPTION_MESSAGE)
                        .isEqualTo(component.getErrorMessage());
            }
            report.assertAll();
        }
}
