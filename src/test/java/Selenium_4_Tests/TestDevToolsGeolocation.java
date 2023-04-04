package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v111.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;

public class TestDevToolsGeolocation {

    public EdgeDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Emulate Geolocation using Selenium 4.0.
     * Method 'executeCdpCommand' is used to mock and emulate Geolocation.
     * The website under test should go after the 'executeCdpCommand' method.
     */
    @Test
    void mockGeolocationCDPCommand() {
        HashMap coordinates = new HashMap() {{
            put("latitude", 34.066540);
            put("longitude", -84.296260);
            put("accuracy", 1);
        }};
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get("https://where-am-i.org");
        assertSoftly(softly -> softly.assertThat(driver.getTitle())
                .isEqualTo("Where Am I? - What is My Location Now on Map"));
    }

    /**
     * Emulate Geolocation using Selenium 4.0.
     * DevTools has a method to mock and emulate Geolocation.
     * The website under test should go after the 'devTools.send' method.
     */
    @Test
    void mockGeolocationDevToolsCommand() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Set The Geolocation Override: Latitude, Longitude, Accuracy.
        devTools.send(Emulation.setGeolocationOverride(Optional.of(41.889980), Optional.of(12.494260), Optional.of(1)));
        driver.get("https://my-location.org/");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).isEqualTo("My Location - Where am I Right Now?"));
    }

    /**
     * Emulate Geolocation using Selenium 4.0.
     * DevTools has a method to mock and emulate Geolocation and Time Zones.
     * The website under test should go after the 'devTools.send' method.
     */
    @Test
    void mockGeolocationDevToolsCommandTimeZone() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Set The Geolocation Override: Latitude, Longitude, Accuracy.
        devTools.send(Emulation.setGeolocationOverride(Optional.of(41.889980), Optional.of(12.494260), Optional.of(1)));
        // Set The Time Zone Override: Time Zone ID.
        devTools.send(Emulation.setTimezoneOverride("Europe/Rome"));
        devTools.send(Emulation.setLocaleOverride(Optional.of("it-IT")));
        driver.get("https://my-location.org/");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).isEqualTo("My Location - Where am I Right Now?"));
    }
}
