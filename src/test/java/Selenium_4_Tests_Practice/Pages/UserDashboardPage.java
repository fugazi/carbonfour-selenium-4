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
        EDIT_PASSWORD_LINK(By.xpath("//a[normalize-space()='Change your password']")),
        EDIT_ADDRESS_LINK(By.xpath("//a[normalize-space()='Modify your address book entries']")),
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
}
