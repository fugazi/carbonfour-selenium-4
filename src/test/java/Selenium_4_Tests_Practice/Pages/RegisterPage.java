package Selenium_4_Tests_Practice.Pages;

import Selenium_4_Tests_Practice.Components.FormFieldComponent;
import Selenium_4_Tests_Practice.Components.InputTextComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class RegisterPage {

    private final WebElement element;

    public RegisterPage(WebElement element) {
        this.element = element;
    }

    private enum Using {
        ERROR_MESSAGES(By.xpath("//div[@class='text-danger']"));

        public final By selector;
        Using(By selector) {
            this.selector = selector;
        }
    }

    /**
     * Interface Form Fields.
     */
    public interface FormField<P> {
        By getSelector();
        FormFieldComponent getComponent(WebElement element, P page);
    }

    /**
     * Enum containing the elements of Register Account Form
     * Also including 'First Name and Last name' fields with the minimum and maximum characters for each component
     */
    public enum FirstAndLastName implements FormField<RegisterPage> {
        FIRST_NAME_INPUT(By.xpath("//input[@id='input-firstname']"), (element, page) -> new InputTextComponent(element, 1, 32)),
        LAST_NAME_INPUT(By.xpath("//input[@id='input-lastname']"), (element, page) -> new InputTextComponent(element, 1, 32));

        final By selector;
        final BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory;

        FirstAndLastName(By selector, BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory) {
            this.selector = selector;
            this.componentFactory = componentFactory;
        }

        @Override
        public By getSelector() {
            return selector;
        }

        @Override
        public FormFieldComponent getComponent(WebElement element, RegisterPage page) {
            return componentFactory.apply(element, (WebDriver) page);
        }
    }

    /**
     * Gets the Error messages from the Register Account Form
     *
     * @return The list containing the error messages
     */
    public List<String> getFieldErrorMessages() {
        return element.findElements(Using.ERROR_MESSAGES.selector).stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
