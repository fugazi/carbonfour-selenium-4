package Selenium_4_Tests_Practice.Utilities;

import Selenium_4_Tests_Practice.Components.EmailComponent;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;

public class EmailUtility extends FormFieldUtility {

        public final String ERROR_EMAIL_MESSAGE = "E-Mail Address does not appear to be valid!";

        private final EmailComponent component;

        /**
        * Constructor for EmailUtility class.
        * @param component EmailComponent
        */
        public EmailUtility(EmailComponent component) {
            this.component = component;
        }

        /**
        * Check the email field.
        * 'Email' field
        * Do assertions on the email field.
        */
        @Override
        public void checkInputText() {
            SoftAssertions report = new SoftAssertions();
            component.addEmailText(RandomStringUtils.randomAlphabetic(component.getMinChar() - 1));
            report.assertThat(ERROR_EMAIL_MESSAGE).isEqualTo(component.getErrorMessage());
            component.addEmailText(RandomStringUtils.randomAlphabetic(component.getMaxChar() + 1));
            report.assertThat(ERROR_EMAIL_MESSAGE).isEqualTo(component.getErrorMessage());
            component.addEmailText(RandomStringUtils.randomAlphabetic(12) + "@testdata.com");
            report.assertThat(component.getErrorMessage()).contains("@testdata.com");
            report.assertAll();
        }
}
