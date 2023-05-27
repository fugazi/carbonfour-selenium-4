package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.NavigationResult;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.edge.EdgeDriver;

public class TestBiDirectional {

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
     * Test to Navigate to a URL with readiness state using Selenium 4.0.
     */
    @Test
    void testNavigateToUrlWithReadinessState() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);
        NavigationResult navigationResult = browsingContext.navigate("https://ecommerce-playground.lambdatest.io",
                ReadinessState.COMPLETE);

        assertSoftly(softly -> {
            softly.assertThat(browsingContext.getId()).isNotNull();
            softly.assertThat(navigationResult.getNavigationId()).isNotNull();
            softly.assertThat(navigationResult.getUrl().contains("ecommerce")).isTrue();
        });
    }
}
