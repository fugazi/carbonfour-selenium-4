package Selenium_4_Tests_Practice.Utilities.Environments;

import java.util.HashMap;
import java.util.Map;

public class BaseDomain {
    /**
     * A map that stores global base URLs for different environments.
     */
    private static final Map<Environment, String> GlobalBaseUrl = new HashMap();

    static {
        GlobalBaseUrl.put(Environment.QA, "qa-webservice.com");
        GlobalBaseUrl.put(Environment.STAGING, "staging-webservice.com");
        GlobalBaseUrl.put(Environment.PROD, "prod-webservice.com");
    }

    /**
     * Determines the environment based on a given base URI.
     *
     * @param baseUri The base URI to analyze.
     * @return The corresponding environment (QA, STAGING, or PROD).
     */
    private static Environment getEnvironmentName(String baseUri) {
        Environment environment = Environment.STAGING;
        if (baseUri.contains("www") || baseUri.contains("webservice.com")) {
            environment = Environment.PROD;
        } else if (baseUri.contains(Environment.QA.getValue())) {
            environment = Environment.QA;
        }
        return environment;
    }

    /**
     * Retrieves the global service URL based on a given base URI.
     *
     * @param baseUri The base URI to determine the environment and fetch the URL.
     * @return The global service URL for the corresponding environment.
     */
    public static String getGlobalServiceURL(String baseUri) {
        Environment environment = getEnvironmentName(baseUri);
        return GlobalBaseUrl.get(environment);
    }
}
