package Selenium_4_Tests_Practice.BaseUtility;

import org.openqa.selenium.WebDriver;

public interface BaseUrl {

    String DOMAIN_ENV_VAR = "domain";
    String DOMAIN_BY_DEFAULT = "ecommerce-playground.lambdatest.io";
    String DOMAIN = System.getenv(DOMAIN_ENV_VAR) != null ? System.getenv(DOMAIN_ENV_VAR) : DOMAIN_BY_DEFAULT;
    String BASE_URL = DOMAIN + "/index.php?route=account/register";
    String BASE_URL_WITH_PROTOCOL = "https://" + BASE_URL;

    /**
     * Initialize the WebDriver instance.
     * Get the current URL and set it as the base URL.
     */
    static void getBaseUrl(WebDriver driver) {
        driver.get(BASE_URL_WITH_PROTOCOL);
        driver.getCurrentUrl();
    }
}
