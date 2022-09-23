package Selenium_4_Tests_Practice.Factory;

public enum CredentialValue {

    ADMIN_USER("selenium@testdata.com", "Colombia+2022"),
    NO_PERMISSIONS_USER("test-no-permissions@testdata.com", "NoPermissions+2022"),
    PUBLIC_USER("public@testdata.com", "Public+2022");
    private final String username;
    private final String password;

    CredentialValue(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the password.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
}

