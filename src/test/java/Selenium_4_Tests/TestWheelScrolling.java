package Selenium_4_Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestWheelScrolling {

    private WebDriver driver;
    private Actions actions;

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
     * Test using built-in method Actions 'Wheel Scrolling' Selenium 4.0.
     */
    @Test
    void testWheelScrolling() {
        actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath("//span[normalize-space()='Home']"))).perform();
        // Scroll down by 500 pixels
        actions.scrollByAmount(0, 500).perform();
        // Scroll up by 500 pixels
        actions.scrollByAmount(0, -500).perform();
    }
}
