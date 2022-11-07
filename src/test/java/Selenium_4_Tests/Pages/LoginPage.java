package Selenium_4_Tests.Pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class LoginPage {

    private enum Using {
        LOGIN_LOGO(By.id("divLogo")),
        LOGIN_PANEL(By.id("logInPanelHeading")),
        LOGIN_IMAGE(By.id("divLoginImage")),
        LOGIN_BUTTON(By.id("divLoginButton")),
        LOGIN_FOOTER(By.id("footer"));

        /**
         * Constructor stub to initialize the selector
         */
        private final By selector;

        Using(By selector) {
            this.selector = selector;
        }
    }

    /**
     * Constructor stub to initialize the WebDriver object.
     */
    public WebDriver driver;

    public LoginPage() {
        super();
    }

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Check if the Login Logo is displayed.
     * The happy way ever < Selenium 4.0.
     *
     * @return true if the Login Logo is displayed
     */
    public boolean isLoginLogoDisplayed() {
        return driver.findElement(Using.LOGIN_LOGO.selector).isDisplayed();
    }

    /**
     * Get the login panel title on the webpage
     * The happy way ever < Selenium 4.0.
     */
    public String getLoginPanelTitle() {
        return driver.findElement(Using.LOGIN_PANEL.selector).getText();
    }

    /**
     * Get the Credentials text using Relative Locators Selenium 4.0.
     * Element to search with the ABOVE method.
     */
    public String getCredentialsTextRelativeLocatorsAbove() {
        WebElement loginPanel = driver.findElement(Using.LOGIN_PANEL.selector);
        WebElement credentials = driver.findElement(RelativeLocator.with(By.tagName("span")).above(loginPanel));
        return credentials.getText();
    }

    /**
     * Get the Username text using Relative Locators Selenium 4.0.
     * Element to search with the BELOW method.
     */
    public String getUsernameTextRelativeLocatorsBelow() {
        WebElement loginPanel = driver.findElement(Using.LOGIN_PANEL.selector);
        WebElement usernameText = driver.findElement(RelativeLocator.with(By.id("txtUsername")).below(loginPanel));
        return usernameText.getText();
    }

    /**
     * Get the Login Image using Relative Locators Selenium 4.0.
     * Element to search with the TO LEFT OF method.
     */
    public boolean getLoginImageRelativeLocatorsLeft() {
        WebElement loginPanel = driver.findElement(Using.LOGIN_PANEL.selector);
        WebElement loginImage = driver.findElement(RelativeLocator.with(By.id("divLoginImage")).toLeftOf(loginPanel));
        return loginImage.isDisplayed();
    }

    /**
     * Get the Login Submit button using Relative Locators Selenium 4.0.
     * Element to search with the TO RIGHT OF method.
     */
    public boolean getLoginSubmitButtonRelativeLocatorsRight() {
        WebElement loginPanel = driver.findElement(Using.LOGIN_IMAGE.selector);
        WebElement loginSubmitButton = driver.findElement(RelativeLocator.with(By.id("btnLogin")).toRightOf(loginPanel));
        return loginSubmitButton.isDisplayed();
    }

    /**
     * Click on the Forgot Password link using Relative Locators Selenium 4.0.
     * Element to search with the NEAR method.
     */
    public Path clickForgotPasswordRelativeLocatorsNear() {
        WebElement loginPanel = driver.findElement(Using.LOGIN_BUTTON.selector);
        WebElement forgotPassword = driver.findElement(RelativeLocator.with(By.id("forgotPasswordLink")).near(loginPanel));
        forgotPassword.click();
        return null;
    }

    /**
     * Get a list of social images from the footer using Relative Locators Selenium 4.0.
     * Element to search with the NEAR method.
     */
    public String getSocialImagesRelativeLocatorsNear() {
        List<WebElement> socialImages = Collections.singletonList(driver.findElement(with(By.tagName("img")).near(Using.LOGIN_FOOTER.selector)));
        return ((WebElement) socialImages).getAttribute("alt");
    }
}
