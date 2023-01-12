package Selenium_4_Tests_Practice;

import static Selenium_4_Tests_Practice.BaseUtility.BaseUrl.getBaseUrl;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Selenium_4_Tests_Practice.Pages.RegisterAccountPage;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.log.Log;
import org.openqa.selenium.edge.EdgeDriver;

@Slf4j
public class RegisterUsabilityTests {

    private static final String REGISTER_ACCOUNT_TITLE = "Register Account";
    private static final String REGISTER_BASE_URL = "https://ecommerce-playground.lambdatest.io/";
    private static final String LOGIN_URL_LINK = "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";
    private static final String PRIVACY_POLICY_LINK = "https://ecommerce-playground.lambdatest.io/index.php?route=information/information/agree&information_id=3";

    public EdgeDriver driver;

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
     * Test to verify that the user is able to load correct Login link.
     * Enable Browser Console Logs using Selenium 4.0.
     */
    @Test
    @Tag("Regression")
    void verifyLoginLink() {
        // Get The DevTools & Create A Session with the EdgeDriver.
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Configure JS exceptions logging
        List<JavascriptException> jsExceptionsList = new ArrayList<>();
        Consumer<JavascriptException> addEntry = jsExceptionsList::add;
        devTools.getDomains().events().addJavascriptExceptionListener(addEntry);

        // Configure console messages logging
        devTools.send(Log.enable());

        // Add A Listener For The Console Logs
        devTools.addListener(Log.entryAdded(), logEntry -> {
            // Print The Logs To The Console
            log.info("--------");
            log.info("Level: " + logEntry.getLevel());
            log.info("Text: " + logEntry.getText());
            log.info("URL: " + logEntry.getUrl());
            log.info("Timestamp: " + logEntry.getTimestamp());
            log.info("StackTrace: " + logEntry.getStackTrace());
            log.info("--------");
        });

        var registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.clickRegisterLink();
        assertSoftly(softly -> softly.assertThat(registerAccountPage.getRegisterAccountTitle())
                .isEqualTo(REGISTER_ACCOUNT_TITLE));
        assertSoftly(softly -> softly.assertThat(driver.getCurrentUrl()).contains(REGISTER_BASE_URL));
        assertSoftly(softly -> softly.assertThat(LOGIN_URL_LINK)
                .isEqualTo(registerAccountPage.loginPageLink().getAttribute("href")));
        assertSoftly(softly -> softly.assertThat(PRIVACY_POLICY_LINK)
                .isEqualTo(registerAccountPage.privacyPolicyLink().getAttribute("href")));
    }

}
