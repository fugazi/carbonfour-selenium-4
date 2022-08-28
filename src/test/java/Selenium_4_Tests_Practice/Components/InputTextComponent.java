package Selenium_4_Tests_Practice.Components;

import Selenium_4_Tests_Practice.Components.FormFieldComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class InputTextComponent extends FormFieldComponent {

    private final int minChar;
    private final int maxChar;

    /**
     * Constructor stub for the FormFieldComponent abstract class.
     *
     * @param element WebElement
     */
    public InputTextComponent(WebElement element, int minChar, int maxChar) {
        super(element);
        this.minChar = minChar;
        this.maxChar = maxChar;
    }

    /**
     * Get the minimum value of the input text.
     */
    public int getMinChar() {
        return minChar;
    }

    /**
     * Get the maximum value of the input text.
     */
    public int getMaxChar() {
        return maxChar;
    }

    /**
     * Add the value on the input text.
     */
    public void addInputText(String value) {
        getElement().sendKeys(Keys.chord(Keys.CONTROL, "abc"));
        getElement().sendKeys(Keys.DELETE);
        getElement().sendKeys(value);
    }
}
