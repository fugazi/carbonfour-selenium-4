package Selenium_4_Tests;

import com.google.common.collect.ImmutableList;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v104.fetch.Fetch;
import org.openqa.selenium.devtools.v104.network.Network;
import org.openqa.selenium.devtools.v104.network.model.BlockedReason;
import org.openqa.selenium.devtools.v104.network.model.ResourceType;
import org.openqa.selenium.devtools.v104.security.Security;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.openqa.selenium.devtools.v104.network.Network.loadingFailed;
import static org.openqa.selenium.remote.http.Contents.utf8String;

@Slf4j
public class TestDevToolsNetworkInterception {

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
        NetworkInterceptor networkInterceptor = new NetworkInterceptor(
                driver,
                // Intercepts all network requests.
                Route.matching(request -> true)
                        .to(() -> request -> new HttpResponse()
                                .setStatus(200)
                                .addHeader("Content-Type", "text/html")
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
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
            } else {
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(url), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
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
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason()).isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.IMAGE)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason()).isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.SCRIPT)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason()).isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.XHR)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason()).isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.MEDIA)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason()).isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            } else if (loadingFailed.getType().equals(ResourceType.WEBSOCKET)) {
                log.info("Blocking reason: " + loadingFailed.getBlockedReason());
                assertSoftly(softly -> softly.assertThat(loadingFailed.getBlockedReason()).isEqualTo(Optional.of(BlockedReason.INSPECTOR)));
            }
        });
        // Block Patterns - In this example: Block some IMG requests.
        devTools.send(Network.setBlockedURLs(List.of("https://ecommerce-playground.lambdatest.io/image/catalog/maza/svg/image2vector.svg",
                "https://ecommerce-playground.lambdatest.io/image/catalog/opencart-logo.png",
                "https://ecommerce-playground.lambdatest.io/catalog/view/theme/mz_poco/asset/stylesheet/megastore-2.28/combine/eba62915f06ab23a214a819a0557a58b.css")));
        // Add a listener to the 'Network' method to get the blocked request
        devTools.addListener(loadingFailed(), loadingFailed -> {
            log.info("Blocking reason final: " + loadingFailed.getBlockedReason().get());
        });
        // Go to the website
        driver.get("https://ecommerce-playground.lambdatest.io");
    }
}
