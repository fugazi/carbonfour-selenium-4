package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.network.Network;
import org.openqa.selenium.devtools.v121.network.model.ConnectionType;
import org.openqa.selenium.edge.EdgeDriver;

public class TestDevToolsNetwork {

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
     * Network Emulation Conditions using Selenium 4.0.
     * DevTools has a method to emulate Network Conditions like connection type, latency, download and upload rate.
     * <a href="Link">https://chromedevtools.github.io/devtools-protocol/tot/Network/</a>
     * The website under test should go after the 'emulateNetworkConditions' method.
     */
    @Test
    void emulateNetworkConditionsCellular3G() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking, network events will now be delivered to the client
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Activates emulation of network conditions for Cellular 3G.
        devTools.send(Network.emulateNetworkConditions(false, 150, 2500, 1500, Optional.of(ConnectionType.CELLULAR3G)));
        driver.get("https://linkedin.com");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("LinkedIn"));
    }

    @Test
    void emulateNetworkConditionsWifi() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking, network events will now be delivered to the client
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Activates emulation of network conditions for Wi-Fi.
        devTools.send(Network.emulateNetworkConditions(false, 250, 8500, 5000, Optional.of(ConnectionType.WIFI)));
        driver.get("https://linkedin.com");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("LinkedIn"));
    }

    @Test
    void emulateNetworkConditionsBluetooth() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking, network events will now be delivered to the client
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Activates emulation of network conditions for Bluetooth.
        devTools.send(Network.emulateNetworkConditions(false, 80, 12400, 1185, Optional.of(ConnectionType.BLUETOOTH)));
        driver.get("https://linkedin.com");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("LinkedIn"));
    }
}
