package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

    /**
     * Locators for the Login page.
     */
    private enum Using {
        MY_ACCOUNT_LINK(By.xpath("//a[@role='button'][normalize-space()='My account']")),
        LOGIN_LINK(By.xpath("//span[normalize-space()='Login']")),
        LOGIN_EMAIL_INPUT(By.xpath("//input[@id='input-email']")),
        LOGIN_PASSWORD_INPUT(By.xpath("//input[@id='input-password']")),
        LOGIN_BUTTON(By.xpath("//input[@value='Login']")),
        RETURNING_CUSTOMER_TITLE(By.xpath("//h2[normalize-space()='Returning Customer']")),
        LOGIN_ERROR_MESSAGE(By.xpath("//div[@class='alert alert-danger alert-dismissible']")),
        USER_LOGIN_DASHBOARD(By.xpath("//h2[normalize-space()='My Account']")),
        USER_DASHBOARD_TABLE(By.xpath("//div[@id='content']"));

        private final By selector;

        Using(By selector) {
            this.selector = selector;
        }
    }

    public WebDriver driver;

    /**
     * Constructor for the LoginPage class.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Mouse over on My Account dropdown and click on Login link.
     * Actions class is used to perform mouse over user interactions.
     */
    public void clickLoginLink() {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(Using.MY_ACCOUNT_LINK.selector)).build().perform();
        driver.findElement(Using.LOGIN_LINK.selector).click();
    }

    /**
     * Login with the email address.
     *
     * @param username The email address in the form.
     */
    public void loginEmailAddress(String username) {
        WebElement loginInput = driver.findElement((Using.LOGIN_EMAIL_INPUT.selector));
        loginInput.clear();
        loginInput.sendKeys(username); }

    /**
     * Login with the password.
     *
     * @param password the user password in the form.
     */
    public void loginPassword(String password) {
        WebElement passwordInput = driver.findElement((Using.LOGIN_PASSWORD_INPUT.selector));
        passwordInput.clear();
        passwordInput.sendKeys(password); }

    /**
     * Click on the Login button.
     */
    public void clickLoginButton() {
        driver.findElement(Using.LOGIN_BUTTON.selector).click(); }

    /**
     * Get the Returning Customer title.
     *
     * @return The Returning Customer title.
     */
    public Boolean getLoginTitle() {
       return driver.findElement(Using.RETURNING_CUSTOMER_TITLE.selector).isDisplayed(); }

    /**
     * Verify that the user is not able to log in with invalid credentials.
     */
    public Boolean verifyLoginErrorMessage() {
        return driver.findElement(Using.LOGIN_ERROR_MESSAGE.selector).isDisplayed(); }

    /**
     * Verify that user is able to join on the user dashboard.
     */
    public Boolean verifyUserLoginDashboard() {
        return driver.findElement(Using.USER_LOGIN_DASHBOARD.selector).isDisplayed(); }

    /**
     * Returns the total number of the table rows
     *
     * @return the total number of the table rows
     */
    public int getTableRowsTotal() {
        return driver.findElements(Using.USER_DASHBOARD_TABLE.selector).size(); }
}
