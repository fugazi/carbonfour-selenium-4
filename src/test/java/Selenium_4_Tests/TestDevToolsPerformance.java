package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.network.Network;
import org.openqa.selenium.devtools.v133.performance.Performance;
import org.openqa.selenium.devtools.v133.performance.model.Metric;
import org.openqa.selenium.edge.EdgeDriver;

public class TestDevToolsPerformance {

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
     * Performance Emulation Conditions using Selenium 4.0.
     * DevTools has a method to emulate Performance Metrics like load time, largest contentful rate, timeline event and layout shift.
     * <a href="Link">https://chromedevtools.github.io/devtools-protocol/tot/PerformanceTimeline/</a>
     * The website under test should go after the 'requestWillBeSent' method.
     */
    @Test
    @DisplayName("Capture HTTP Request Performance Metrics")
    void testPerformanceCaptureHttpRequests() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables network tracking, requests events will be now delivered to the client
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Add an event Listener for the 'requestWillBeSent' event.
        devTools.addListener(Network.requestWillBeSent(), event -> System.out.println("Request URI: " + event.getRequest().getUrl() + "\n" + "Request URI + Assertions: "
                + event.getRequest().getUrl().contains("linkedin.com") + "\n" + "Request Type: " + event.getType()
                + "\n" + "Request Method: " + event.getRequest().getMethod() + "\n" + "Request Headers: "
                + event.getRequest().getHeaders() + "\n" + "Request Post Data: " + event.getRequest()
                + "\n" + "Request Mixed Content Type: " + event.getRequest().getMixedContentType() + "\n"
                + "Request Referrer Policy: " + event.getRequest().getReferrerPolicy() + "\n"
                + "Request Is Signed Exchanged: " + event.getRequest().getUrlFragment().isPresent() + "\n"
                + "Request Document URL: " + event.getDocumentURL() + "\n" + "Request Initiator: "
                + event.getInitiator() + "\n" + "Request Timestamp: " + event.getTimestamp() + "\n"));
        // Active URL under test
        driver.get("https://linkedin.com");
        assertSoftly(softly -> softly.assertThat(driver.getTitle()).contains("LinkedIn"));
        devTools.send(Network.disable());
    }

    @Test
    @DisplayName("Performance Metrics objects")
    void testPerformanceMetrics() {
        // Get The DevTools & Create A Session with the ChromeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enables performance tracking, requests events will be shown in the timeline panel.
        devTools.send(Performance.enable(Optional.empty()));
        // Active URL under test
        driver.get("https://linkedin.com");
        // Return a list of Performance Metrics objects then stream to get all the names of the metrics captured
        List<Metric> performanceMetrics = devTools.send(Performance.getMetrics());
        List<String> metricNames = performanceMetrics.stream().map(Metric::getName).collect(Collectors.toList());
        // Disables performance tracking
        devTools.send(Performance.disable());
        // Print the metrics captured
        List<String> metricsAssert = getStringList(performanceMetrics, metricNames);
        assertSoftly(softly -> softly.assertThat(metricNames).containsAll(metricsAssert));
    }

    private static List<String> getStringList(List<Metric> performanceMetrics, List<String> metricNames) {
        List<String> metricsAssert = Arrays.asList("Timestamp", "Documents", "Frames", "JSEventListeners", "Nodes",
                "LayoutCount", "RecalcStyleCount", "RecalcStyleDuration", "LayoutDuration", "MediaKeySessions",
                "Resources", "DomContentLoaded", "NavigationStart", "TaskDuration", "JSHeapUsedSize", "JSHeapTotalSize",
                "ScriptDuration");
        // Assert that the metrics captured are the expected metrics
        metricsAssert.forEach(metric -> System.out.println(
                "Metric: " + metric + "\n" + performanceMetrics.get(metricNames.indexOf(metric)).getValue()));
        return metricsAssert;
    }
}
