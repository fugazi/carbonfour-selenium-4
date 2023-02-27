
# Selenium 4 - UI Tests Project

This is an Automation project using the new features for Selenium Webdriver 4.6 version.

This project has been developed by [Douglas Urrea Ocampo](https://www.linkedin.com/in/douglasfugazi) to improve learning on advanced Automation testing with Selenium 4.6.

The main features of this project has the following:

* You find the latest and greatest features and techniques for Selenium 4.6 to improve your testing!
* Page Object Model (POM) as a design pattern for creating an Object directory for web UI elements.
* Use industry-standard best practices for writing tests with Selenium 4.6.
* The re-usability of code to avoid unnecessary data exposure to the user by using the abstraction.
* JUnit as framework for writing test automation tests for Java programming language.
* Selenium Webdriver that supports comprehensive Web Tests.

> ⚠️ **Disclaimer** ⚠️
>
> This project has an educational objective: To learn the newest features for Selenium 4.6.
>
> Some practices will help you to improve your test architecture, but the central point of this repository is
> demonstrate with examples the new and exciting features of Selenium 4.6.
> 
> You can find more information about the new features with the official links:
> * [Selenium 4.6](https://www.seleniumhq.org/docs/04_dev_guide.jsp#selenium-4-0)
> * [Selenium Webdriver documentation](https://www.selenium.dev/documentation/webdriver/)
> * [Chrome DevTools documentation](https://chromedevtools.github.io/devtools-protocol/)

## 🏠 Tester details
* Name: `Douglas Urrea Ocampo`
* Country: `Colombia`
* City: `Medellin`
* E-mail: `douglas@douglasfugazi.co`
* LinkedIn: [https://www.linkedin.com/in/douglasfugazi](https://www.linkedin.com/in/douglasfugazi)
* Contact: [https://douglasfugazi.co](https://douglasfugazi.co)

## 🏀 Languages and Frameworks
This project using the following languages and frameworks:

* [Java 11](https://openjdk.java.net/projects/jdk/11/) as the programming language
* [JUnit](https://junit.org/junit5/) as the UnitTest framework to support the test creation
* [Selenium WebDriver](https://www.selenium.dev/) as the web browser automation framework using the latest 4.6. version
* [AssertJ](https://joel-costigliola.github.io/assertj/) as the fluent assertion library
* [Log4J2](https://logging.apache.org/log4j/2.x/) as the logging management strategy
* ~~[WebDriverManager](https://github.com/bonigarcia/webdrivermanager) as the Selenium binaries management~~
* [Owner](http://owner.aeonbits.org/) to minimize the code to handle the properties file
* [Project Lombok](https://projectlombok.org) as fully featured builder library
* [Java Faker](https://github.com/DiUS/java-faker) allows us to generate fake data

### ⚡️Automation Test Strategy
We know that any automation project starting with a good test architecture.

This projects has UI Tests using Selenium Webdriver with latest 4.6. version.

The website under test is an e-commerce website [here](https://ecommerce-playground.lambdatest.io) to test different domain access according to the scope of each test.

I must say it was fun learning and automating this website because it was challenging in some respects. 😀

1. Browser is Microsoft Edge in order to have a different approach to test.
2. Test has following scenarios:
    * Scenario 1:
        * How To Use Chrome DevTools Protocol (CDP) In Selenium 4.6
          * DevTools: Console Logs
          * Geolocation
          * Network Emulation: 3G, 4G, Wi-Fi, Bluetooth
    * Scenario 2:
        * How To Use Element Position In Selenium 4.6
            * Element Position of an Image using built-in method 'getRect()'
            * Size & position to fetch the width & height of an element.
    * Scenario 3:
        * How To Use Relative Locators In Selenium 4.6
            * Each relative locator is a method to be searched by: Above, Below, Left, Right, Near of.
    * Scenario 4:
        * How To Take Screenshots In Selenium 4.6
            * Method 'getScreenshotAs' is used to take a WebElement screenshot.
            * Method 'getFullPageScreenshotAs' is used to take a Full Page screenshot.
    * Scenario 5:
        * How To Use Window and Tab Management In Selenium 4.6
    * Scenario 6:
        * How To Use CDP command to capture Performance Metrics In Selenium 4.6
            * Time to First Byte (TTFB): To identify when a web server takes too long to respond to requests.
            * First Contentful Paint (FCP): First time in the page load timeline when the user can see anything on the screen.
            * Largest Contentful Paint (LCP): It denotes the point in the page load timeline when the page's main content has most likely loaded.
            * First Input Delay (FID): It quantifies the experience users feel when attempting to interact with non-responsive pages.
            * Time to Interactive (TTI): It helps identify cases where a page appears interactive but is not.
            * Total Blocking Time (TBT): It helps quantify the severity of a page's non-interactivity before it becomes reliably interactive.
            * Cumulative Layout Shift (CLS): It helps in quantifying how frequently users encounter unexpected layout shifts.
   * Scenario 7:
       * How To Simulate Devices Viewport In Selenium 4.6
           * Google Chrome
           * Firefox
           * Microsoft Edge
           * Internet Explorer 11
           * Safari
           * Opera
           * iPhone X
           * iPhone SE
           * iPad Pro
           * Galaxy Note 10
           * Galaxy Tablet
           * Pixel 6 XL
           * OnePlus 10
   * Scenario 8:
       * How To Simulate Network Interception In Selenium 4.6
           * Network Security to intercept network requests and block requests based on the conditions.
           * Network Block Patterns to block URLs and requests patterns.
           * WebSocket Listener to intercept WebSocket requests.
           * Event Message Listener to intercept source events.
           * Intercept HTTP traffic and responses.
           * Request Cache to intercept requests served from cache.
           * Response Override to intercept responses served from cache.
3. After the test, assertions will be validated to each test.
4. Annotation @Test will be used to group the tests by Smoke and Regression tests.
5. Each tests will finish automatically and the browser will be closed.

**New Scenarios:**
* Practice Test:
  * First Approach: Test the Register Account page.
    * Verify the error messages count and red alert text without filling the Register Account Form.
    * Negative test cases.
  * Second Approach: Test the Register Account page with Page Object Model (POM).
    * Parameterized test with MethodSource to perform the test for each field and do assertions.
    * Negative test cases.
  * Third Approach: Test the Register Account page with Page Object Model (POM) and Data Driven.
    * Precondition Scenario: Load the Test Data to perform the Register Account page.
    * Test to verify that the user is able to Register an Account.
    * Assertions to each field.
* Accessibility Testing: Perform accessibility test on the website under Test.
    * The test will display in logs if there are any accessibility rule violations.

## 🟢 Pre-requisites: 🟢
1. Download the latest Java JDK from [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133155.html)
2. Install git from [here](https://git-scm.com)
3. Selenium Webdriver from [here](https://www.selenium.dev)
~~4. Get WebDriverManager from [here](https://bonigarcia.dev/webdrivermanager/)~~
5. Install Apache Maven from [here](https://maven.apache.org)
6. Test Runner is JUnit 5 from [here](https://junit.org/junit5/)
7. Build Tool is Maven the latest version 
8. Note: ChromeDriver.exe is not necessary, you should use the Selenium Manager capabilities. 
9. Note: POM.xml has all configuration installed, you should use the Maven build tool only.

## 🛠️ Running the project:
1. Download the project from GitHub
    * Option 1: `git clone https://github.com/fugazi/carbonlibre-ui-api-selenium.git`
    * Option 2: Download it as a Zip file and extract it
2. CD into the `Carbon Four > carbonfour-selenium-4` folder
3. Set up Maven
    1. Run the following command in the terminal:
        * Option 1: `mvn clean`
        * Option 2: `mvn clean package`
4. Running Tests in the project
    * Option 1: run `mvn clean install` in the path of the project
    * Option 2: Open the project baseline and run each Selenium Test: `src/test/java/Selenium_4_Tests`
5. Enjoy the project! 😎

## ☎️ Generating the test report
This project uses Allure Report to automatically generate the test report.
There are some configuration to make it happen:
* aspectj configuration on `pom.xml` file
* `allure.properties` file on `src/test/resources`

You can use the command line to generate it in two ways:
* `mvn allure:serve`: will open the HTML report into the browser
* `mvn allure:report`: will generate the HTML port at `target/site/allure-maven-plugin` folder

## 🚀 Project configuration
* Selenium project Baseline: `Carbon Four`
    * Project package `Selenium_4_Tests` into folder `src/test/java/Selenium_4_Tests`
    * Selenium UI Tests `Test.java` into folder `src/test/java/Selenium_4_Tests`
    * Page Objects `CategoriesPage.java` into folder `src/test/java/Selenium_4_Tests/Pages`
    * Screenshots package `Selenium 4/Screenshots` into folder `src/test/java/com.selenium_4/Screenshots`
    * **New Scenarios** Selenium 4.0 Test Practices into folder `src/test/java/Selenium_4_Tests_Practice`
* testName: `carbonfour-selenium-4`

## 💡 Do you want to help?
* If you have an idea, suggestion, feature or an issue, please open a new Pull Request on [GitHub](https://github.com/fugazi/carbonfour-selenium-4/pulls)
* Pull requests without explanations will be rejected. No hard feelings, we are open to all suggestions.

[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://douglasfugazi.co/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/douglasfugazi)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/douglasfugazi)
[![MIT License](https://img.shields.io/github/license/fugazi/carbonfour-selenium-4)](https://github.com/fugazi/carbonfour-selenium-4/blob/main/LICENSE)

