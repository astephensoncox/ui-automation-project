package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginErrorPage;
import pages.LoginPage;
import utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidLoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @Test
    public void testInvalidLogin() {
        log.info("Starting invalid login test");

        driver.get(ConfigReader.get("baseUrl"));
        log.info("Navigated to login page");

        LoginPage login = new LoginPage(driver);
        login.enterUsername("wrongUser");
        login.enterPassword("wrongPass");
        login.clickLogin();

        LoginErrorPage error = new LoginErrorPage(driver);
        String message = error.getErrorMessage();

        log.info("Error message received: " + message);

        Assert.assertTrue(message.contains("Your username is invalid")
                        || message.contains("Your password is invalid"),
                "Expected error message not found.");

        log.info("Invalid login test completed");
    }
}
