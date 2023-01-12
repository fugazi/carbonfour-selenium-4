package Selenium_4_Tests;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.io.IOException;

import Selenium_4_Tests.Pages.ScreenshotsPage;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TestScreenshots {

    /**
     * Instantiate the ScreenshotsPage class to the Webdriver object.
     */
    ScreenshotsPage screenshotsPage = new ScreenshotsPage();

    /**
     * Check if the Shop Logo is displayed. The happy way ever < Selenium 4.0.
     */
    @Test
    @Tag("Smoke")
    void shopLogoSmokeTest() {
        screenshotsPage.setupUrl();
        assertSoftly(softly -> softly.assertThat(screenshotsPage.isShopLogoDisplayed())
                .describedAs("E-Commerce LambdaTest is loaded. Shop logo is displayed")
                .isTrue());
        screenshotsPage.tearDown();
    }

    /**
     * Take a screenshot of the Shop Logo using Selenium 4.0.
     * Method 'getScreenshotAs' is used to take a WebElement screenshot.
     */
    @Test
    @Tag("Regression")
    void takeWebElementScreenshot() {
        screenshotsPage.setupUrl();
        try {
            screenshotsPage.takeShopLogoScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertSoftly(softly -> softly.assertThat(screenshotsPage.getShopTitle()).isEqualTo("Your Store"));
        screenshotsPage.tearDown();
    }

    /**
     * Take a screenshot of the Shop Categories using Selenium 4.0.
     * Method 'getScreenshotAs' is used to take a WebElement screenshot.
     */
    @Test
    @Tag("Regression")
    void takeWebElementPageSectionScreenshot() {
        screenshotsPage.setupUrl();
        try {
            screenshotsPage.takeShopCategoriesScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertSoftly(softly -> softly.assertThat(screenshotsPage.getShopTitle()).isEqualTo("Your Store"));
        screenshotsPage.tearDown();
    }

    /**
     * Take a screenshot of the Shop Full Page using Selenium 4.0.
     * Method 'getFullPageScreenshotAs' is used to take a Full Page screenshot.
     */
    @Test
    @Tag("Regression")
    void takeFullPageScreenshot() {
        screenshotsPage.setupUrl();
        try {
            screenshotsPage.takeShopContentScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertSoftly(softly -> softly.assertThat(screenshotsPage.getShopTitle()).isEqualTo("Your Store"));
        screenshotsPage.tearDown();
    }
}
