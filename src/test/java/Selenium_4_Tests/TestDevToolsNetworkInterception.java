package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.openqa.selenium.devtools.v123.network.Network.*;
import static org.openqa.selenium.remote.http.Contents.utf8String;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v122.fetch.Fetch;
import org.openqa.selenium.devtools.v122.network.Network;
import org.openqa.selenium.devtools.v122.network.model.BlockedReason;
import org.openqa.selenium.devtools.v122.network.model.ResourceType;
import org.openqa.selenium.devtools.v122.security.Security;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import com.google.common.collect.ImmutableList;

@Slf4j
public class TestDevToolsNetworkInterception {

    private static final Integer PAUSE_TIME = 5000;

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
     * Network Interception using Selenium 4.0.
     * DevTools has a method to intercept network requests: 'NetworkInterceptor'
     * The website under test should go after the 'Route.matching' method.
     */
    @Test
    void interceptNetworkRequests() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking, network events will now be delivered to the client
        NetworkInterceptor networkInterceptor = new NetworkInterceptor(driver,
                // Intercepts all network requests.
                Route.matching(request -> true)
                        .to(() -> request -> new HttpResponse().setStatus(200).addHeader("Content-Type", "text/html")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .setContent(utf8String("Network Intercepted!"))));
        // Go to the website
        driver.get("https://linkedin.com");
        String pageSource = driver.getPageSource();
        assertSoftly(softly -> softly.assertThat(pageSource).contains("Network Intercepted!"));
    }

    /**
     * Network Security using Selenium 4.0.
     * DevTools has a method to intercept network requests and block requests: 'Fetch.requestPaused'
     * The website under test should go after the 'Network Listener' method.
     */
    @Test
    void networkFetchTracking() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking with the 'Fetch' method, network events will now be delivered to the client
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        // Add a new network request listener
        devTools.addListener(Fetch.requestPaused(), request -> {
            // Get the request URL
            String url = request.getRequest().getUrl();
            // If the url is in the list of blocked urls, block the request
            if (url.contains("linkedin.com")) {
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty()));
            } else {
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(url), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty()));
            }
            // Go to the website
            driver.get("https://linkedin.com");
            assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("LinkedIn"));
        });
    }

    /**
     * Network Block Patterns using Selenium 4.0.
     * DevTools has a method to intercept network requests and block patterns: 'setBlockedURLs'
     * The website under test should go after the 'Network Listener' method.
     */
    @Test
    void networkBlockPatterns() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables security tracking with the 'Security' method, security events will now be delivered to the client
        devTools.send(Security.setIgnoreCertificateErrors(true));
        // Block all requests patterns
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*")));
        // Add a new request listener
        devTools.addListener(loadingFailed(), loadingFailed -> {
            if (loadingFailed.getType().equals(ResourceType.STYLESHEET)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason())
                        .isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.IMAGE)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason())
                        .isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.SCRIPT)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason())
                        .isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.XHR)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason())
                        .isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.MEDIA)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason())
                        .isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.WEBSOCKET)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason())
                        .isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            }
        });
        // Block Patterns - In this example: Block some IMG requests.
        devTools.send(Network.setBlockedURLs(
                List.of("https://ecommerce-playground.lambdatest.io/image/catalog/maza/svg/image2vector.svg",
                        "https://ecommerce-playground.lambdatest.io/image/catalog/opencart-logo.png",
                        "https://ecommerce-playground.lambdatest.io/catalog/view/theme/mz_poco/asset/stylesheet/megastore-2.28/combine/eba62915f06ab23a214a819a0557a58b.css")));
        // Add a listener to the 'Network' method to get the blocked request
        devTools.addListener(loadingFailed(), loadingFailed -> {
            log.info("Blocking reason final: " + loadingFailed.getBlockedReason().get());
        });
        // Go to the website
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    /**
     * WebSocket Listener using Selenium 4.0.
     * DevTools has a method to intercept WebSocket requests and create a listener: 'webSocketCreated'
     * The website under test should go after the 'webSocketClosed' method.
     */
    @Test
    public void verifyWebSocketOperationTest() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking with the 'Enable' method, network events will now be delivered to the client
        devTools.send(enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Add a new WebSocket listener
        devTools.addListener(webSocketCreated(), ws -> {
            log.info("WebSocket created: " + ws.getUrl());
            log.info("WebSocket id: " + ws.getRequestId());
            log.info("WebSocket type: " + ws.getInitiator().stream().findFirst().get().getType());
        });
        // Received WebSocket listener
        devTools.addListener(webSocketFrameReceived(), e -> {
            log.info("WebSocket frame received: " + e.getRequestId());
            log.info(e.getResponse().getPayloadData());
            log.info(e.getResponse().getOpcode().toString());
            log.info(String.valueOf(e.getResponse().getMask()));
        });
        // Get WebSocket error listener
        devTools.addListener(webSocketFrameError(), e -> {
            log.info("WebSocket error: " + e.getErrorMessage());
        });
        // Close WebSocket listener
        devTools.addListener(webSocketClosed(), c -> {
            log.info("WebSocket Closed");
            log.info(String.valueOf(c.getTimestamp()));
        });
        // Go to the website and open a WebSocket connection
        driver.get("https://www.piesocket.com/websocket-tester");
        var button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        setPause();
        var closeButton = driver.findElement(By.xpath("//button[normalize-space()='Disconnect']"));
        closeButton.click();
        setPause();
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Online WebSocket"));
    }

    /**
     * Event Message Listener using Selenium 4.0.
     * DevTools has a method to intercept Event source and create a listener: 'eventSourceMessageReceived'
     * The website under test should go after the 'addListener' method.
     */
    @Test
    void verifyEventSourceMessagesTest() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking with the 'Enable' method, network events will now be delivered to the client
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Add a new Event Source listener
        devTools.addListener(eventSourceMessageReceived(), e -> {
            log.info("Event Source event data received: " + e.getData());
            log.info("Event Source event name: " + e.getEventName());
            log.info("Event Source event id: " + e.getEventId());
            log.info("Event Source message id: " + e.getRequestId());
            log.info("Event Source event time: " + e.getTimestamp());
        });
        // Go to the website and open an Event Source connection
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml5_sse");
        setPause();
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Editor"));
    }

    /**
     * Get HTTP traffic using Selenium 4.0.
     * DevTools has a method to intercept HTTP requests and create a listener: 'responseReceived'
     * The website under test should go after the 'addListener' method.
     */
    @Test
    void getHttpTrafficTest() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking with the 'Enable' method, network events will now be delivered to the client
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Add a new HTTP listener
        devTools.addListener(responseReceived(), e -> {
            log.info("HTTP response received: " + e.getRequestId());
            log.info("HTTP response url: " + e.getResponse().getUrl());
            log.info("HTTP response status: " + e.getResponse().getStatus());
            log.info("HTTP response status text: " + e.getResponse().getStatusText());
            log.info("HTTP response headers: " + e.getResponse().getHeaders());
            log.info("HTTP response protocol: " + e.getResponse().getProtocol());
            log.info("HTTP response remote IP address: " + e.getResponse().getRemoteIPAddress());
            log.info("HTTP response remote port: " + e.getResponse().getRemotePort());
            log.info("HTTP response mime type: " + e.getResponse().getMimeType());
            log.info("HTTP response connection id: " + e.getResponse().getConnectionId());
            log.info("---");
        });
        // Go to the website
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    /**
     * Get Request served from Cache using Selenium 4.0.
     * DevTools has a method to intercept network requests: 'requestServedFromCache'
     * The website under test should go after the 'addListener' method.
     */
    @Test
    void getRequestServedFromCacheTest() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking with the 'Enable' method, network events will now be delivered to the client
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Clear Browser Cache
        devTools.send(Network.setCacheDisabled(true));
        devTools.send(Network.clearBrowserCache());
        devTools.send(Network.clearBrowserCookies());
        // Add a new HTTP listener
        devTools.addListener(requestServedFromCache(), cacheRequest -> {
            log.info("HTTP request served from cache: " + cacheRequest);
        });
        // Go to the website
        driver.get("https://ecommerce-playground.lambdatest.io");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("Your Store"));
    }

    /**
     * Sets a pause on the page load.
     */
    private void setPause() {
        Actions actions = new Actions(driver);
        actions.pause(PAUSE_TIME).perform();
    }
}
