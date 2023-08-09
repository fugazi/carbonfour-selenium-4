package Selenium_4_Tests_Practice.Pages;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import Selenium_4_Tests_Practice.Components.EmailComponent;
import Selenium_4_Tests_Practice.Components.FormFieldComponent;
import Selenium_4_Tests_Practice.Components.InputTextComponent;
import Selenium_4_Tests_Practice.Components.PasswordComponent;
import Selenium_4_Tests_Practice.Components.TelephoneComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RegisterPage {

    private final WebDriver driver;

    /**
     * Constructor for the RegisterPage class.
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Locators for the Register Account page.
     */
    private enum Using {
        MY_ACCOUNT_LINK(By.xpath("//a[@role='button'][normalize-space()='My account']")),
        REGISTER_LINK(By.xpath("//span[normalize-space()='Register']")),
        REGISTER_ACCOUNT_TITLE(By.xpath("//h1")),
        ERROR_MESSAGES(By.xpath("//div[@class='text-danger']")),
        CONTINUE_BUTTON(By.xpath("//input[@value='Continue']"));

        private final By selector;

        Using(By selector) {
            this.selector = selector;
        }
    }

    /**
     * Interface Form Fields to validate the form fields.
     */
    public interface FormField<WebDriver> {
        By getSelector();

        FormFieldComponent getComponent(WebElement element, WebDriver page);
    }

    /**
     * Enum containing the elements of Register Account Form
     * Also including 'First Name and Last name' fields with the minimum and maximum characters for each component.
     * Functional Interface for the FormFieldComponent that represents a function that accepts two arguments and produces a result.
     */
    public enum FirstAndLastName implements FormField<RegisterPage> {
        FIRST_NAME_INPUT(By.xpath("//input[@id='input-firstname']"),
                (element, page) -> new InputTextComponent(element, page, 1, 32)),
        LAST_NAME_INPUT(By.xpath("//input[@id='input-lastname']"),
                (element, page) -> new InputTextComponent(element, page, 1, 32));

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
            return componentFactory.apply(element, page.driver);
        }
    }

    /**
     * Enum containing the elements of Register Account Form
     * Also including 'Email' field with the minimum and maximum characters for each component.
     * Functional Interface for the FormFieldComponent that represents a function that accepts two arguments and produces a result.
     */
    public enum Email implements FormField<RegisterPage> {
        EMAIL_INPUT(By.xpath("//input[@id='input-email']"),
                (element, page) -> new EmailComponent(element, page, 1, 36));

        final By selector;
        final BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory;

        Email(By selector, BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory) {
            this.selector = selector;
            this.componentFactory = componentFactory;
        }

        @Override
        public By getSelector() {
            return selector;
        }

        @Override
        public FormFieldComponent getComponent(WebElement element, RegisterPage page) {
            return componentFactory.apply(element, page.driver);
        }
    }

    /**
     * Enum containing the elements of Register Account Form
     * Also including 'Telephone' field with the minimum and maximum characters for each component.
     * Functional Interface for the FormFieldComponent that represents a function that accepts two arguments and produces a result.
     */
    public enum Telephone implements FormField<RegisterPage> {
        TELEPHONE_INPUT(By.xpath("//input[@id='input-telephone']"),
                (element, page) -> new TelephoneComponent(element, page, 3, 32));

        final By selector;
        final BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory;

        Telephone(By selector, BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory) {
            this.selector = selector;
            this.componentFactory = componentFactory;
        }

        @Override
        public By getSelector() {
            return selector;
        }

        @Override
        public FormFieldComponent getComponent(WebElement element, RegisterPage page) {
            return componentFactory.apply(element, page.driver);
        }
    }

    /**
     * Enum containing the elements of Register Account Form
     * Also including 'Password' field with the minimum and maximum characters for each component.
     * Functional Interface for the FormFieldComponent that represents a function that accepts two arguments and produces a result.
     */
    public enum Password implements FormField<RegisterPage> {
        PASSWORD_INPUT(By.xpath("//input[@id='input-password']"),
                (element, page) -> new PasswordComponent(element, page, 4, 20));

        final By selector;
        final BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory;

        Password(By selector, BiFunction<WebElement, WebDriver, FormFieldComponent> componentFactory) {
            this.selector = selector;
            this.componentFactory = componentFactory;
        }

        @Override
        public By getSelector() {
            return selector;
        }

        @Override
        public FormFieldComponent getComponent(WebElement element, RegisterPage page) {
            return componentFactory.apply(element, page.driver);
        }
    }

    /**
     * FormFieldComponent Final
     */
    public FormFieldComponent getFormFieldComponent(final FormField<RegisterPage> formField) {
        return formField.getComponent(driver.findElement(formField.getSelector()), this);
    }

    /**
     * Mouse over on My Account and click on Register link.
     * Actions class is used to perform mouse over user interactions.
     */
    public void clickMyAccountLink() {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(Using.MY_ACCOUNT_LINK.selector)).build().perform();
        driver.findElement(Using.REGISTER_LINK.selector).click();
    }

    /**
     * Get the Register Account Title.
     *
     * @return Register Account Title.
     */
    public String getRegisterAccountTitle() {
        return driver.findElement(Using.REGISTER_ACCOUNT_TITLE.selector).getText();
    }

    /**
     * Click on the Continue button
     */
    public void clickContinueButton() {
        driver.findElement(Using.CONTINUE_BUTTON.selector).click();
    }

    /**
     * Gets the Error messages from the Register Account Form
     *
     * @return The list containing the error messages
     */
    public List<String> getFieldErrorMessages() {
        return driver.findElements(Using.ERROR_MESSAGES.selector).stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
