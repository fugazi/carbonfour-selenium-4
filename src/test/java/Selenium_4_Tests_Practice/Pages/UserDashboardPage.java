package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
        INPUT_LAST_NAME(By.id("input-lastname")),
        INPUT_COMPANY(By.id("input-company")),
        INPUT_ADDRESS_1(By.id("input-address-1")),
        INPUT_ADDRESS_2(By.id("input-address-2")),
        INPUT_CITY(By.id("input-city")),
        INPUT_POSTCODE(By.id("input-postcode")),
        SELECT_COUNTRY(By.id("input-country")),
        SELECT_REGION_STATE(By.id("input-zone")),
        CHECKBOX_DEFAULT_ADDRESS(By.xpath("//input[@value='0']")),
        BUTTON_CONTINUE(By.xpath("//input[@value='Continue']"));

        private final By selector;

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

    /**
     * Sets the Company.
     *
     * @param company Company in the form.
     */
    public void enterCompany(String company) {
        WebElement companyInput = driver.findElement(Using.INPUT_COMPANY.selector);
        companyInput.clear();
        companyInput.sendKeys(company);
    }

    /**
     * Gets the entered value of the Company.
     *
     * @return Company value.
     */
    public String getCompany() {
        return driver.findElement(Using.INPUT_COMPANY.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Address 1.
     *
     * @param address1 Address 1 in the form.
     */
    public void enterAddress1(String address1) {
        WebElement address1Input = driver.findElement(Using.INPUT_ADDRESS_1.selector);
        address1Input.clear();
        address1Input.sendKeys(address1);
    }

    /**
     * Gets the entered value of the Address 1.
     *
     * @return Address 1 value.
     */
    public String getAddress1() {
        return driver.findElement(Using.INPUT_ADDRESS_1.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Address 2.
     *
     * @param address2 Address 2 in the form.
     */
    public void enterAddress2(String address2) {
        WebElement address2Input = driver.findElement(Using.INPUT_ADDRESS_2.selector);
        address2Input.clear();
        address2Input.sendKeys(address2);
    }

    /**
     * Gets the entered value of the Address 2.
     *
     * @return Address 2 value.
     */
    public String getAddress2() {
        return driver.findElement(Using.INPUT_ADDRESS_2.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the City.
     *
     * @param city City in the form.
     */
    public void enterCity(String city) {
        WebElement cityInput = driver.findElement(Using.INPUT_CITY.selector);
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    /**
     * Gets the entered value of the City.
     *
     * @return City value.
     */
    public String getCity() {
        return driver.findElement(Using.INPUT_CITY.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Post Code.
     *
     * @param postCode Post Code in the form.
     */
    public void enterPostcode(String postCode) {
        WebElement postCodeInput = driver.findElement(Using.INPUT_POSTCODE.selector);
        postCodeInput.clear();
        postCodeInput.sendKeys(postCode);
    }

    /**
     * Gets the entered value of the Post Code.
     *
     * @return Post Code value.
     */
    public String getPostcode() {
        return driver.findElement(Using.INPUT_POSTCODE.selector).getAttribute(VALUE_ATTRIBUTE);
    }

    /**
     * Sets the Country.
     *
     * @param country Country in the form.
     */
    public void selectCountry(String country) {
        Select countrySelect = new Select(driver.findElement(Using.SELECT_COUNTRY.selector));
        countrySelect.selectByVisibleText(country);
    }

    /**
     * Gets the selected value of the Country.
     *
     * @return Country value.
     */
    public String getCountry() {
        Select countrySelect = new Select(driver.findElement(Using.SELECT_COUNTRY.selector));
        return countrySelect.getFirstSelectedOption().getText();
    }

    /**
     * Sets the Region tate.
     *
     * @param state Region State in the form.
     */
    public void selectRegionState(String state) {
        Select stateSelect = new Select(driver.findElement(Using.SELECT_REGION_STATE.selector));
        stateSelect.selectByVisibleText(state);
    }

    /**
     * Gets the selected value of the Region State.
     *
     * @return Region State value.
     */
    public String getRegionState() {
        Select stateSelect = new Select(driver.findElement(Using.SELECT_REGION_STATE.selector));
        return stateSelect.getFirstSelectedOption().getText();
    }

    /**
     * Click on the Default Address checkbox.
     */
    public void clickDefaultAddressCheckbox() {
        driver.findElement(Using.CHECKBOX_DEFAULT_ADDRESS.selector).click(); }

    /**
     * Click on the Continue button.
     */
    public void clickContinueButton() {
        driver.findElement(Using.BUTTON_CONTINUE.selector).click(); }
}
