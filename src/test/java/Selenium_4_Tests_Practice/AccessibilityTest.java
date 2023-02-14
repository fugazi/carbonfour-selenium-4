package Selenium_4_Tests_Practice;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.deque.html.axecore.args.AxeRunOptions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;

@Slf4j
public class AccessibilityTest {

    WebDriver driver;

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
     * Perform accessibility test on the website under Test.
     * The test will display in logs if there are any accessibility rule violations.
     */
    @Test
    void accessibilityTest() {
        AxeRunOptions axeRunOptions = new AxeRunOptions();
        axeRunOptions.setXPath(true);

        AxeBuilder axeBuilder = new AxeBuilder()
                .withOptions(axeRunOptions)
                .withTags(Arrays.asList("wcag2a", "wcag2aa", "wcag21aa"))
                .disableRules(Collections.singletonList("color-contrast"))
                .disableIframeTesting();

        Results results = axeBuilder.analyze(driver);
        List<Rule> violations = results.getViolations();
        log.info("Violations: {}", violations);
    }
}
