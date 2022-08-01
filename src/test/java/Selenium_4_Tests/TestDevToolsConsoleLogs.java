package Selenium_4_Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.devtools.v85.log.Log;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class TestDevToolsConsoleLogs {

    public EdgeDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://my-location.org");
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Test to view Browser Console Logs using Selenium 4.0.
     */
    @Test
    void viewBrowserConsoleLogs() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable The Console Logs
        devTools.send(Log.enable());

        // Add A Listener For The Console Logs
        devTools.addListener(Log.entryAdded(), logEntry -> {
            // Print The Logs
            System.out.println("--------");
            System.out.println("Level: " + logEntry.getLevel());
            System.out.println("Text: " + logEntry.getText());
            System.out.println("URL: " + logEntry.getUrl());
            System.out.println("Timestamp: " + logEntry.getTimestamp());
            System.out.println("StackTrace: " + logEntry.getStackTrace());
        });
        assertSoftly(softly -> {
            softly.assertThat(driver.getTitle()).isEqualTo("My Location - Where am I Right Now?");
        });
    }
}
