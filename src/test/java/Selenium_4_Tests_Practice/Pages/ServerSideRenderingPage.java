package Selenium_4_Tests_Practice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ServerSideRenderingPage {

    private final WebDriver driver;

    /**
     * Constructor for the ServerSideRenderingPage class.
     */
    public ServerSideRenderingPage(WebDriver driver) {
        this.driver = driver; }

    /**
     * Locators for the Server Side Rendering page.
     */
    private enum Using {
        MAIN_HEADER(By.id("main-header")),
        MAIN_NAVIGATION(By.id("main-navigation"));

        public final By selector;

        Using(By selector) {
            this.selector = selector; }
    }

    /**
     * Server Side Rendering (SSR) is the process of rendering pages on the server, before sending them to the browser.
     * This means that the browser downloads a page that is already populated with content,
     * instead of having to wait for the JavaScript to download and execute before the browser can display the page.
     * <p>
     * Verifies if the "page-rendered" property is equal to "true" on the Server Side Rendering page.
     * @return {@code true} if the "page-rendered" property is equal to "true", indicating that Server Side Rendering is enabled
     * Otherwise, it returns {@code false}.
     */

    /**
     * Get the Main Header on the Server Side Rendering page.
     *
     * @return Main Header.
     */
    public Boolean getMainHeader() {
        return driver.findElement(Using.MAIN_HEADER.selector).isDisplayed(); }

    /**
     * Get the Main Navigation on the Server Side Rendering page.
     *
     * @return Main Navigation.
     */
    public Boolean getMainNavigation() {
        return driver.findElement(Using.MAIN_NAVIGATION.selector).isDisplayed(); }

    public Boolean pageRenderedProperty() {
        return driver.findElement(By.tagName("html")).getAttribute("page-rendered").equals("true"); }
}
