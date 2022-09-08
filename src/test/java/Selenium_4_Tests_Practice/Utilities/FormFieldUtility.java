package Selenium_4_Tests_Practice.Utilities;

import Selenium_4_Tests_Practice.Components.EmailComponent;
import Selenium_4_Tests_Practice.Components.FormFieldComponent;
import Selenium_4_Tests_Practice.Components.InputTextComponent;
import Selenium_4_Tests_Practice.Components.TelephoneComponent;

public abstract class FormFieldUtility {

    /**
     * Get the instance of the FormFieldComponent.
     * @param formFieldComponent instance of the InputTextComponent
     * @return IllegalArgumentException if the formFieldComponent is not an instance of InputTextComponent
     */

    public static FormFieldUtility getInstance(FormFieldComponent formFieldComponent) {
        if (formFieldComponent instanceof InputTextComponent) {
            return new InputTextUtility((InputTextComponent) formFieldComponent);
        } else if (formFieldComponent instanceof EmailComponent) {
            return new EmailUtility((EmailComponent) formFieldComponent);
        } else if (formFieldComponent instanceof TelephoneComponent) {
            return new TelephoneUtility((TelephoneComponent) formFieldComponent);
        }
        throw new IllegalArgumentException("Error Unknown component");
    }

    public abstract void checkInputText();
}
