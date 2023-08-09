package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RegisterAccountPage {

    private static final String VALUE_ATTRIBUTE = "value";
    private final WebDriver driver;

    /**
     * Constructor for the RegisterAccountPage class.
     */
    public RegisterAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Locators for the Register Account page.
     */
    private enum Using {
        MY_ACCOUNT_LINK(By.xpath("//a[@role='button'][normalize-space()='My account']")),
        REGISTER_LINK(By.xpath("//span[normalize-space()='Register']")),
        REGISTER_ACCOUNT_TITLE(By.xpath("//h1")),
        LOGIN_PAGE_LINK(By.xpath("//h1/following-sibling::p/a")),
        INPUT_FIRST_NAME(By.id("input-firstname")),
        INPUT_LAST_NAME(By.id("input-lastname")),
        INPUT_EMAIL(By.id("input-email")),
        INPUT_TELEPHONE(By.id("input-telephone")),
        INPUT_PASSWORD(By.id("input-password")),
        INPUT_PASSWORD_CONFIRM(By.id("input-confirm")),
        RADIO_NEWSLETTER(By.xpath("//label[normalize-space()='No']")),
        PRIVACY_POLICY_CHECKBOX(By.xpath("//label[@for='input-agree']")),
        PRIVACY_POLICY_LINK(By.xpath("//a[@class='agree']")),
        CONTINUE_BUTTON(By.xpath("//input[@value='Continue']"));

        private final By selector;

        Using(By selector) {
            this.selector = selector;
        }
    }

    /**
     * Mouse over on My Account dropdown and click on Register link.
     * Actions class is used to perform mouse over user interactions.
     */
    public void clickRegisterLink() {
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
     * Get the Login Page link.
     *
     * @return Login Page link.
     */
    public WebElement loginPageLink() {
        return driver.findElement(Using.LOGIN_PAGE_LINK.selector); }

    /**
     * Sets the First Name.
     *
     * @param firstName First Name in the form.
     */
    public void enterFirstName(String firstName) {
        WebElement firstNameInput = driver.findElement(Using.INPUT_FIRST_NAME.selector);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    /**
     * Gets the entered value of the First Name.
     *
     * @return First Name value.
     */
    public String getFirstName() {
        return driver.findElement(Using.INPUT_FIRST_NAME.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Last Name.
     *
     * @param lastName Last Name in the form.
     */
    public void enterLastName(String lastName) {
        WebElement lastNameInput = driver.findElement(Using.INPUT_LAST_NAME.selector);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    /**
     * Gets the entered value of the Last Name.
     *
     * @return Last Name value.
     */
    public String getLastName() {
        return driver.findElement(Using.INPUT_LAST_NAME.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Email.
     *
     * @param email Email in the form.
     */
    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(Using.INPUT_EMAIL.selector);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    /**
     * Gets the entered value of the Email.
     *
     * @return Email value.
     */
    public String getEmail() {
        return driver.findElement(Using.INPUT_EMAIL.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Telephone.
     *
     * @param telephone Telephone in the form.
     */
    public void enterTelephone(String telephone) {
        WebElement telephoneInput = driver.findElement(Using.INPUT_TELEPHONE.selector);
        telephoneInput.clear();
        telephoneInput.sendKeys(telephone);
    }

    /**
     * Gets the entered value of the Telephone.
     *
     * @return Telephone value.
     */
    public String getTelephone() {
        return driver.findElement(Using.INPUT_TELEPHONE.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Password.
     *
     * @param password Password in the form.
     */
    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(Using.INPUT_PASSWORD.selector);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Gets the entered value of the Password.
     *
     * @return Password value.
     */
    public String getPassword() {
        return driver.findElement(Using.INPUT_PASSWORD.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Password Confirm.
     *
     * @param passwordConfirm Password Confirm in the form.
     */
    public void enterPasswordConfirm(String passwordConfirm) {
        WebElement passwordConfirmInput = driver.findElement(Using.INPUT_PASSWORD_CONFIRM.selector);
        passwordConfirmInput.clear();
        passwordConfirmInput.sendKeys(passwordConfirm);
    }

    /**
     * Gets the entered value of the Password Confirm.
     *
     * @return Password Confirm value.
     */
    public String getPasswordConfirm() {
        return driver.findElement(Using.INPUT_PASSWORD_CONFIRM.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Clicks on the Newsletter radio button.
     */
    public void clickNewsletterRadioButton() {
        driver.findElement(Using.RADIO_NEWSLETTER.selector).click();
    }

    /**
     * Checks if the Newsletter radio button is selected.
     *
     * @return true if the radio button is selected, false otherwise.
     */
    public boolean isNewsletterRadioButtonSelected() {
        return driver.findElement(Using.RADIO_NEWSLETTER.selector).isSelected();
    }

    /**
     * Clicks on the Privacy Policy checkbox.
     */
    public void clickPrivacyPolicyCheckbox() {
        driver.findElement(Using.PRIVACY_POLICY_CHECKBOX.selector).click();
    }

    /**
     * Checks if the Privacy Policy checkbox is selected.
     *
     * @return true if the checkbox is selected, false otherwise.
     */
    public boolean isPrivacyPolicyCheckboxSelected() {
        return driver.findElement(Using.PRIVACY_POLICY_CHECKBOX.selector).isSelected();
    }

    /**
     * Get the privacy policy link.
     *
     * @return Privacy Policy link.
     */
    public WebElement privacyPolicyLink() {
        return driver.findElement(Using.PRIVACY_POLICY_LINK.selector);
    }

    /**
     * Click on the Continue button
     */
    public void clickContinueButton() {
        driver.findElement(Using.CONTINUE_BUTTON.selector).click();
    }

}
