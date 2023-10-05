package Selenium_4_Tests_Practice.Utilities.Environments;

public enum Environment {
    QA("qa"),
    STAGING("staging"),
    PROD("prod");

    private String environmentName;

    /**
     * Constructor to initialize an Environment with a specific name.
     *
     * @param environmentName The name of the environment.
     */
    Environment(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getValue() {
        return environmentName;
    }
}
