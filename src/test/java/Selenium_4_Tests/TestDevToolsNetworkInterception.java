package Selenium_4_Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v104.fetch.Fetch;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.util.Optional;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.openqa.selenium.remote.http.Contents.utf8String;

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
     * Network Security Patterns using Selenium 4.0.
     * DevTools has a method to intercept network requests and block patterns: 'setBlockedURLs'
     * The website under test should go after the 'Network Listener' method.
     */
    @Test
    void blackHoleNetworkPatterns() {
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
}
