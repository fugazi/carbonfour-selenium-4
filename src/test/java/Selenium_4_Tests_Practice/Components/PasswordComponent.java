package Selenium_4_Tests_Practice.Components;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PasswordComponent extends FormFieldComponent {

    private final int minChar;
    private final int maxChar;

    /**
     * Constructor stub for the FormFieldComponent abstract class.
     *
     * @param element WebElement instance
     * @param page    WebDriver instance
     * @param minChar minimum number of characters
     * @param maxChar maximum number of characters
     */
    public PasswordComponent(WebElement element, WebDriver page, int minChar, int maxChar) {
        super(element, page);
        this.minChar = minChar;
        this.maxChar = maxChar;
    }

    /**
     * Get the minimum value of the password text.
     * 'Password' input field
     */
    public int getMinChar() {
        return minChar;
    }

    /**
     * Get the maximum value of the password text.
     * 'Password' input field
     */
    public int getMaxChar() {
        return maxChar;
    }

    /**
     * Add the value on the password text.
     * 'Password' field
     */
    public void addPasswordText(String value) {
        getElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getElement().sendKeys(Keys.DELETE);
        getElement().sendKeys(value);
    }
}
