package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;

public class TestDevToolsDeviceMode {

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
     * Test to Simulate Device Mode using Selenium 4.0.
     * Method 'Emulation.setDeviceMetricsOverride' is used to emulate viewport dimensions and screen orientation to trigger the responsiveness of the application.
     */
    @Test
    @DisplayName("Google Chrome Emulation")
    void simulatingDeviceModeGoogleChrome() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Emulate Device Mode. This includes width, height, deviceScaleFactor, and mobile.
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 800);
        deviceMetrics.put("height", 600);
        deviceMetrics.put("mobile", true);
        deviceMetrics.put("deviceScaleFactor", 50);

        // Add screen orientation as a nested map
        Map<String, Object> screenOrientation = new HashMap<>();
        screenOrientation.put("type", "landscapePrimary");
        screenOrientation.put("angle", 50);

        deviceMetrics.put("screenOrientation", screenOrientation);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Firefox Emulation")
    void simulatingDeviceModeFirefox() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 700);
        deviceMetrics.put("height", 500);
        deviceMetrics.put("mobile", true);
        deviceMetrics.put("deviceScaleFactor", 70);

        // Add screen orientation as a nested map
        Map<String, Object> screenOrientation = new HashMap<>();
        screenOrientation.put("type", "portraitPrimary");
        screenOrientation.put("angle", 20);

        deviceMetrics.put("screenOrientation", screenOrientation);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Microsoft Edge Emulation")
    void simulatingDeviceModeMicrosoftEdge() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 1024);
        deviceMetrics.put("height", 768);
        deviceMetrics.put("mobile", true);
        deviceMetrics.put("deviceScaleFactor", 70);

        // Add screen orientation as a nested map
        Map<String, Object> screenOrientation = new HashMap<>();
        screenOrientation.put("type", "portraitSecondary");
        screenOrientation.put("angle", 45);

        deviceMetrics.put("screenOrientation", screenOrientation);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Internet Explorer 11 Emulation")
    void simulatingDeviceModeIE11() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Set Time Zone Emulation
        devTools.send(Emulation.setTimezoneOverride("America/New_York"));

        // Simulating Device Mode
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 1600);
        deviceMetrics.put("height", 1200);
        deviceMetrics.put("mobile", true);
        deviceMetrics.put("deviceScaleFactor", 50);

        // Add screen orientation as a nested map
        Map<String, Object> screenOrientation = new HashMap<>();
        screenOrientation.put("type", "landscapeSecondary");
        screenOrientation.put("angle", 25);

        deviceMetrics.put("screenOrientation", screenOrientation);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Safari Emulation")
    void simulatingDeviceModeSafari() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 800);
            put("height", 800);
            put("mobile", true);
            put("deviceScaleFactor", 90);
            put("screenOrientation", new HashMap() {{
                put("type", "landscapePrimary");
                put("angle", 90);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Opera Emulation")
    void simulatingDeviceOpera() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 900);
            put("height", 700);
            put("mobile", true);
            put("deviceScaleFactor", 50);
            put("screenOrientation", new HashMap() {{
                put("type", "portraitPrimary");
                put("angle", 0);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("iPhone X Emulation")
    void simulatingDeviceIPhoneX() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 375);
            put("height", 812);
            put("mobile", true);
            put("deviceScaleFactor", 0);
            put("DisplayFeature", new HashMap() {{
                put("orientation", "horizontal");
                put("offset", 0);
                put("maskLength", 0);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("iPhone SE Emulation")
    void simulatingDeviceIPhoneSE() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 320);
            put("height", 568);
            put("mobile", true);
            put("deviceScaleFactor", 0);
            put("DisplayFeature", new HashMap() {{
                put("orientation", "vertical");
                put("offset", 0);
                put("maskLength", 0);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Galaxy Note 10 Emulation")
    void simulatingDeviceGalaxyNote() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 412);
            put("height", 869);
            put("mobile", true);
            put("deviceScaleFactor", 0);
            put("DisplayFeature", new HashMap() {{
                put("orientation", "horizontal");
                put("offset", 0);
                put("maskLength", 0);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Pixel 6 Emulation")
    void simulatingDevicePixelXL() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 420);
            put("height", 933);
            put("mobile", true);
            put("deviceScaleFactor", 0);
            put("DisplayFeature", new HashMap() {{
                put("orientation", "vertical");
                put("offset", 0);
                put("maskLength", 0);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("iPad Pro Emulation")
    void simulatingDeviceIPadPro() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 1024);
            put("height", 1366);
            put("mobile", true);
            put("deviceScaleFactor", 0);
            put("DisplayFeature", new HashMap() {{
                put("orientation", "vertical");
                put("offset", 0);
                put("maskLength", 0);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    @Test
    @DisplayName("Galaxy Tablet Emulation")
    void simulatingDeviceGalaxyTablet() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Simulating Device Mode
        HashMap deviceMetrics = new HashMap() {{
            put("width", 1134);
            put("height", 712);
            put("mobile", true);
            put("deviceScaleFactor", 0);
            put("DisplayFeature", new HashMap() {{
                put("orientation", "vertical");
                put("offset", 0);
                put("maskLength", 0);
            }});
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }
}
