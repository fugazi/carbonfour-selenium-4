package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        LOGIN_ERROR_MESSAGE(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));

        public final By selector;

        Using(By selector) {
            this.selector = selector;
        }
    }

    private final WebDriver driver;

    /**
     * Constructor for the LoginPage class.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Click on the My Account link.
     */
    public void clickMyAccountLink() {
        driver.findElement(Using.MY_ACCOUNT_LINK.selector).click(); }

    /**
     * Click on the Login link.
     */
    public void clickLoginLink() {
        driver.findElement(Using.LOGIN_LINK.selector).click(); }

    /**
     * Login with the email address.
     *
     * @param username The email address.
     */
    public void loginEmailAddress(String username) {
        driver.findElement((Using.LOGIN_EMAIL_INPUT.selector)); }

    /**
     * Login with the password.
     *
     * @param password the user password.
     */
    public void loginPassword(String password) {
        driver.findElement((Using.LOGIN_PASSWORD_INPUT.selector)); }

    /**
     * Click on the Login button.
     */
    public void clickLoginButton() {
        driver.findElement(Using.LOGIN_BUTTON.selector).click(); }

    /**
     * Verify that the user is on the Login page.
     */
    public void verifyLoginTitle() {
        driver.findElement(Using.RETURNING_CUSTOMER_TITLE.selector); }

    /**
     * Verify that the user is not able to log in with invalid credentials.
     */
    public Boolean verifyLoginErrorMessage() {
        return driver.findElement(Using.LOGIN_ERROR_MESSAGE.selector).isDisplayed(); }
}
