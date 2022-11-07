package Selenium_4_Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Set;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class TestWindowManagement {

    public WebDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io");
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Test opening a new WINDOW using built-in method 'Window and Tab Management' Selenium 4.0.
     */
    @Test
    void testNewWindowSelenium() {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        assertSoftly(softly -> {
            softly.assertThat(driver.getTitle()).isEqualTo("Google");
        });
    }

    /**
     * Test opening a new TAB using built-in method 'Window and Tab Management' Selenium 4.0.
     */
    @Test
    void testNewTabSelenium() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com");
        assertSoftly(softly -> {
            softly.assertThat(driver.getTitle()).contains("YouTube");
        });
    }

    /**
     * Test Automatically Open & Switch To The New WINDOW or TAB using built-in method 'Window and Tab Management' Selenium 4.0.
     * Switch & Work In The Main Window Or Tab
     */
    @Test
    void testWorkingInBothWindowTab() {
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.google.com");
        // Work In The New Window Or Tab
        WebElement searchBoxTab = driver.findElement(By.name("q"));
        searchBoxTab.sendKeys("Selenium 4.0");
        searchBoxTab.sendKeys(Keys.ENTER);
        assertSoftly(softly -> {
            softly.assertThat(driver.getTitle()).contains("Google");
        });
        // Get The Window ID Handles
        Set<String> parentWindowId = driver.getWindowHandles();
        // Switch To The Parent Window
        driver.switchTo().window(parentWindowId.iterator().next());
        // Work In The Parent Window Or Tab
        WebElement searchBoxParent = driver.findElement(By.xpath("(//input[@placeholder='Search For Products'])[1]"));
        searchBoxParent.sendKeys("iPhone");
        searchBoxParent.sendKeys(Keys.ENTER);
        assertSoftly(softly -> {
            softly.assertThat(driver.getTitle()).isEqualTo("Search - iPhone");
        });
    }

}
