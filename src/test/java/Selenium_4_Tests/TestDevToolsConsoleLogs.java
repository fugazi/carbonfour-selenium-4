package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v149.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDevToolsConsoleLogs {

    private static final Logger log = LoggerFactory.getLogger(TestDevToolsConsoleLogs.class);

    public EdgeDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
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
            // Print The Logs To The Console
            log.info("--------");
            log.info("Level: {}", logEntry.getLevel());
            log.info("Text: {}", logEntry.getText());
            log.info("URL: {}", logEntry.getUrl());
            log.info("Timestamp: {}", logEntry.getTimestamp());
            log.info("StackTrace: {}", logEntry.getStackTrace());
            log.info("--------");
        });
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).isEqualTo("My Location - Where am I Right Now?"));
    }
}
