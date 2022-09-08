package Selenium_4_Tests_Practice.Components;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputTextComponent extends FormFieldComponent {

    private final int minChar;
    private final int maxChar;

    /**
     * Constructor stub for the FormFieldComponent abstract class.
     *
     * @param element WebElement instance
     * @param page WebDriver instance
     * @param minChar minimum number of characters
     * @param maxChar maximum number of characters
     */
    public InputTextComponent(WebElement element, WebDriver page, int minChar, int maxChar) {
        super(element, page);
        this.minChar = minChar;
        this.maxChar = maxChar;
    }

    /**
     * Get the minimum value of the input text.
     * 'First Name and Last name' fields
     */
    public int getMinChar() {
        return minChar;
    }

    /**
     * Get the maximum value of the input text.
     * 'First Name and Last name' fields
     */
    public int getMaxChar() {
        return maxChar;
    }

    /**
     * Add the value on the input text.
     * 'First Name and Last name' fields
     */
    public void addInputText(String value) {
        getElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getElement().sendKeys(Keys.DELETE);
        getElement().sendKeys(value);
    }
}
