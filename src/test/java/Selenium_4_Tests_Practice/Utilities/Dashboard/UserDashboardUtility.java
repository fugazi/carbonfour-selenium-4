package Selenium_4_Tests_Practice.Utilities.Dashboard;

import Selenium_4_Tests_Practice.Factory.AddNewAddressFactory;
import Selenium_4_Tests_Practice.Models.AddNewAddressData;
import Selenium_4_Tests_Practice.Pages.UserDashboardPage;

public class UserDashboardUtility {

    private final UserDashboardPage userDashboardPage;
    private final AddNewAddressData addNewAddressData;

    /**
     * Constructor for the UserDashboardUtility class.
     *
     * @param userDashboardPage instance of Pages object
     */
    public UserDashboardUtility(UserDashboardPage userDashboardPage) {
        this.userDashboardPage = userDashboardPage;
        this.addNewAddressData = AddNewAddressFactory.getAddNewAddressData();}

    /**
     * Add a New Address Book to the User Dashboard.
     * This method uses the AddNewAddressFactory object with the Builder pattern.
     * Fill the data on the Add New Address form.
     */
    public void addNewAddressBook() {
        userDashboardPage.clickAddressBookLink();
        userDashboardPage.clickNewAddressButton();
        userDashboardPage.enterFirstName(addNewAddressData.getFirstName());
        userDashboardPage.enterLastName(addNewAddressData.getLastName());
        userDashboardPage.enterCompany(addNewAddressData.getCompany());
        userDashboardPage.enterAddress1(addNewAddressData.getAddress1());
        userDashboardPage.enterAddress2(addNewAddressData.getAddress2());
        userDashboardPage.enterCity(addNewAddressData.getCity());
        userDashboardPage.enterPostcode(addNewAddressData.getPostcode());
        userDashboardPage.selectCountry(addNewAddressData.getCountry());
        userDashboardPage.selectRegionState(addNewAddressData.getRegionState());
        userDashboardPage.clickDefaultAddressCheckbox();
        userDashboardPage.clickContinueButton();
    }
}
