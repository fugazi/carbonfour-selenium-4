package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class RegisterAccountPage {

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
        REGISTER_ACCOUNT_TITLE(By.xpath("//h1"));

        public final By selector;

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

}
