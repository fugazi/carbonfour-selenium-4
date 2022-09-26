package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDashboardPage {

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
        LOGOUT_LINK(By.xpath("//a[normalize-space()='Logout']"));

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
}
