package Selenium_4_Tests_Practice.Utilities;

import Selenium_4_Tests_Practice.Factory.CredentialValue;
import Selenium_4_Tests_Practice.Pages.LoginPage;

public class LoginPageUtility {

    private final LoginPage loginPage;

    /**
     * Constructor for the LoginPageUtility class.
     */
    public LoginPageUtility(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    /**
     * Adds the user and password to the login page using the ADMIN_USER credentials
     */
    public void authenticateWithAdminCredentials() {
        authenticateAs(CredentialValue.ADMIN_USER);
    }

    /**
     * Adds the user and password to the login page using the NO_PERMISSIONS_USER credentials
     */
    public void authenticateWithNoPermissionsCredentials() {
        authenticateAs(CredentialValue.NO_PERMISSIONS_USER);
    }

    /**
     * Adds the user and password to the login page using the PUBLIC_USER credentials
     */
    public void authenticateWithPublicUserCredentials() {
        authenticateAs(CredentialValue.PUBLIC_USER);
    }

    /**
     * Adds the user and password to the login page and clicks on the login button
     *
     * @param username the username to be added to the login page
     *                 and used to authenticate the user in the form.
     * @param password the password to be added to the login page
     *                 and used to authenticate the user in the form.
     */
    public void loginWithCredentials(String username, String password) {
        loginPage.getLoginTitle();
        loginPage.loginEmailAddress(username);
        loginPage.loginPassword(password);
        loginPage.clickLoginButton();
    }

    /**
     * Authenticates the application using the credentials provided
     */
    private void authenticateAs(CredentialValue credentialValue) {
        loginWithCredentials(credentialValue.getUsername(), credentialValue.getPassword());
    }

}
