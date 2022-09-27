package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserDashboardPage {

    private static final String VALUE_ATTRIBUTE = "value";
    private final WebDriver driver;

    /**
     * Constructor for the UserDashboardPage class.
     */
    public UserDashboardPage(WebDriver driver) {
        this.driver = driver; }

    /**
     * Locators for the User Dashboard page.
     */
    private enum Using {
        DASHBOARD_MY_ACCOUNT_TITLE(By.xpath("//h2[normalize-space()='My Account']")),
        USER_DASHBOARD_TABLE(By.xpath("//div[@id='content']")),
        ADDRESS_BOOK_LINK(By.xpath("//a[normalize-space()='Address Book']")),
        ADD_NEW_ADDRESS_BUTTON(By.xpath("//a[normalize-space()='New Address']")),
        INPUT_FIRST_NAME(By.id("input-firstname")),
        INPUT_LAST_NAME(By.id("input-lastname"));

        public final By selector;

        Using(By selector) {
            this.selector = selector; }
    }

    /**
     * Get My Account title on the Dashboard page.
     *
     * @return My Account title.
     */
    public Boolean getMyAccountTitleDashboard() {
        return driver.findElement(Using.DASHBOARD_MY_ACCOUNT_TITLE.selector).isDisplayed(); }

    /**
     * Returns the total number of the table rows
     *
     * @return the total number of the table rows
     */
    public int getTableRowsTotal() {
        return driver.findElements(Using.USER_DASHBOARD_TABLE.selector).size(); }

    /**
     * Click on the Address Book link.
     */
    public void clickAddressBookLink() {
        driver.findElement(Using.ADDRESS_BOOK_LINK.selector).click(); }

    /**
     * Click on the Add New Address button.
     */
    public void clickNewAddressButton() {
        driver.findElement(Using.ADD_NEW_ADDRESS_BUTTON.selector).click(); }

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
}
