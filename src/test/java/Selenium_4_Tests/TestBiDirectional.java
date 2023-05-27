package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContextInfo;
import org.openqa.selenium.bidi.browsingcontext.NavigationResult;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Test for use a combination of W3C BiDi protocol APIs.
 */
class TestBiDirectional {

    public FirefoxDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setupUrl() {
        driver = new FirefoxDriver();
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

    /**
     * Test to creates a new browsing context in a new window using Selenium 4.0.
     */
    @Test
    void testCreateAWindow() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.WINDOW);

        assertSoftly(softly -> {
            softly.assertThat(browsingContext.getId()).isNotNull();
            softly.assertThat(browsingContext.getId().equals(driver.getWindowHandle())).isTrue();
        });
    }

    /**
     * Test to Create a Browsing Context for given an ID using Selenium 4.0.
     */
    @Test
    void testCreateABrowsingContextForGivenId() {
        String id = driver.getWindowHandle();
        BrowsingContext browsingContext = new BrowsingContext(driver, id);

        assertSoftly(softly -> {
            softly.assertThat(browsingContext.getId()).isNotNull();
            softly.assertThat(browsingContext.getId().equals(id)).isTrue();
        });
    }

    /**
     * Test to creates a new tab using Selenium 4.0.
     */
    @Test
    void testCreateANewTab() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);

        assertSoftly(softly -> {
            softly.assertThat(browsingContext.getId()).isNotNull();
            softly.assertThat(browsingContext.getId().equals(driver.getWindowHandle())).isTrue();
        });
    }

    /**
     * Test to creates a new tab with a reference context using Selenium 4.0.
     */
    @Test
    void testCreateATabWithAReferenceContext() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB, driver.getWindowHandle());

        assertSoftly(softly -> {
            softly.assertThat(browsingContext.getId()).isNotNull();
            softly.assertThat(browsingContext.getId().equals(driver.getWindowHandle())).isTrue();
        });
    }

    /**
     * Test to Navigate to a URL using Selenium 4.0.
     */
    @Test
    void testNavigateToAUrl() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);
        NavigationResult info = browsingContext.navigate("https://ecommerce-playground.lambdatest.io");

        assertSoftly(softly -> {
            softly.assertThat(browsingContext.getId()).isNotNull();
            softly.assertThat(info.getNavigationId()).isNotNull();
            softly.assertThat(info.getUrl().contains("ecommerce")).isTrue();
        });
    }

    /**
     * Test to Get browsing context tree using Selenium 4.0
     * Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context.
     */
    @Test
    void testGetTreeWithAChild() {
        String referenceContextId = driver.getWindowHandle();
        BrowsingContext parentWindow = new BrowsingContext(driver, referenceContextId);
        parentWindow.navigate("https://ecommerce-playground.lambdatest.io", ReadinessState.COMPLETE);
        List<BrowsingContextInfo> contextInfoList = parentWindow.getTree();
        BrowsingContextInfo info = contextInfoList.get(0);

        assertSoftly(softly -> {
            softly.assertThat(contextInfoList.size()).isEqualTo(1);
            softly.assertThat(contextInfoList.get(0).getId()).isEqualTo(referenceContextId);
            softly.assertThat(contextInfoList.get(1).getId()).isNotEqualTo(referenceContextId);
            softly.assertThat(info.getChildren().size()).isEqualTo(1);
            softly.assertThat(info.getChildren().get(0).getId()).isNotEqualTo(referenceContextId);
            softly.assertThat(info.getChildren().get(0).getUrl().contains("ecommerce")).isTrue();
        });
    }

    /**
     * Test to Get browsing context tree with depth using Selenium 4.0
     * Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context upto the depth value passed.
     */
    @Test
    void testGetTreeWithDepth() {
        String referenceContextId = driver.getWindowHandle();
        BrowsingContext parentWindow = new BrowsingContext(driver, referenceContextId);
        parentWindow.navigate("https://ecommerce-playground.lambdatest.io", ReadinessState.COMPLETE);
        List<BrowsingContextInfo> contextInfoList = parentWindow.getTree(0);
        BrowsingContextInfo info = contextInfoList.get(0);

        assertSoftly(softly -> {
            softly.assertThat(contextInfoList.size()).isEqualTo(1);
            softly.assertThat(contextInfoList.get(0).getId()).isEqualTo(referenceContextId);
            softly.assertThat(contextInfoList.get(1).getId()).isNotEqualTo(referenceContextId);
            softly.assertThat(contextInfoList.get(0).getChildren().size()).isEqualTo(0);
            softly.assertThat(info.getChildren()).isNull();
        });
    }

    /**
     * Test to Get All Top level browsing contexts
     */
    @Test
    void testGetAllTopLevelContexts() {
        BrowsingContext window1 = new BrowsingContext(driver, driver.getWindowHandle());
        List<BrowsingContextInfo> contextInfoList = window1.getTopLevelContexts();

        assertSoftly(softly -> softly.assertThat(contextInfoList.size()).isEqualTo(2));
    }
}
