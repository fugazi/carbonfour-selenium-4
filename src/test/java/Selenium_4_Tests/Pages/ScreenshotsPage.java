package Selenium_4_Tests.Pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotsPage {

    private enum Using {
        SHOP_LOGO(By.xpath("//img[@alt='Poco Electro']")), SHOP_CATEGORIES(By.xpath("//div[@class='entry-module module-mz_product_listing left-title ']")), SHOP_CONTENT(By.xpath("//div[@id='common-home']"));

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

    public ScreenshotsPage() {
        super();
    }

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Check if the Shop Logo is displayed.
     * The happy way ever < Selenium 4.0.
     *
     * @return true if the Shop Logo is displayed
     */
    public boolean isShopLogoDisplayed() {
        return driver.findElement(Using.SHOP_LOGO.selector).isDisplayed();
    }

    /**
     * Get the shop title on the webpage
     * The happy way ever < Selenium 4.0.
     *
     * @return the shop title
     */
    public String getShopTitle() {
        return driver.getTitle();
    }

    /**
     * Get the Shop Logo title.
     * Take a screenshot of the Shop Logo.
     * Method 'getScreenshotAs' is used to take a WebElement screenshot.
     */
    public void takeShopLogoScreenshot() throws IOException {
        WebElement shopLogoScreenshot = driver.findElement(Using.SHOP_LOGO.selector);
//        FileHandler.createDir(new File("src/test/java/com/selenium_4/screenshots"));
        File source = shopLogoScreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File("src/test/java/com/selenium_4/screenshots/shopLogo.png");
        FileHandler.copy(source, destination);
    }

    /**
     * Get the Shop Categories section.
     * Take a screenshot of the Shop Categories.
     * Method 'getScreenshotAs' is used to take a WebElement screenshot.
     */
    public void takeShopCategoriesScreenshot() throws IOException {
        WebElement shopCategoriesScreenshot = driver.findElement(Using.SHOP_CATEGORIES.selector);
        File source = shopCategoriesScreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File("src/test/java/com/selenium_4/screenshots/shopCategories.png");
        FileHandler.copy(source, destination);
    }

    /**
     * Get the Full Page of the website under test.
     * Take a screenshot of the Shop Content.
     * Method 'getFullPageScreenshotAs' is used to take a Full Page screenshot.
     */
    public void takeShopContentScreenshot() throws IOException {
        File source = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
        File destination = new File("src/test/java/com/selenium_4/screenshots/shopContent.png");
        FileHandler.copy(source, destination);
    }

}
