package Selenium_4_Tests_Practice;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import Selenium_4_Tests_Practice.Pages.ServerSideRenderingPage;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

@Slf4j
class ServerSideRenderingTest {
    private WebDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        getBaseUrl(driver);
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Verifies if SSR (Server Side Rendering) is active on a specific page.
     * @param url - the URL of the page to be tested.
     * This method performs a test to ensure that SSR is properly enabled on the page.
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ServerSideRenderingURL.txt")
    void serverSideRenderingTest(String url) {
        driver.get(url);
        log.info("Server Side Rendering is active on {}", url);
        var ssrPage = new ServerSideRenderingPage(driver);
        assertSoftly(softly -> {
            softly.assertThat(ssrPage.getMainHeader())
                    .withFailMessage("Server Side Rendering is not active on " + url).isTrue();
            softly.assertThat(ssrPage.getMainNavigation())
                    .withFailMessage("Server Side Rendering is not active on " + url).isTrue();
        });
    }
}
