package Selenium_4_Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

public class TestWheelScrolling {

    private WebDriver driver;

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
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath("//span[normalize-space()='Home']"))).perform();
        // Scroll down by 500 pixels
        actions.scrollByAmount(0, 500).perform();
        // Scroll up by 500 pixels
        actions.scrollByAmount(0, -500).perform();
        // Scroll to an element
        actions.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(
                driver.findElement(By.xpath("//h3[normalize-space()='From The Blog']"))),0, 500).perform();
        // Scroll from Viewport
        actions.scrollFromOrigin(WheelInput.ScrollOrigin.fromViewport(),0, 500).perform();
        actions.scrollFromOrigin(WheelInput.ScrollOrigin.fromViewport(35, 35),0, 500).perform();
    }
}
