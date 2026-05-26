package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import pages.SecureAreaPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @Test
    public void testLogin() {
        log.info("Starting valid login test");

        driver.get(ConfigReader.get("baseUrl"));
        log.info("Navigated to login page");

        LoginPage login = new LoginPage(driver);
        login.enterUsername("tomsmith");
        login.enterPassword("SuperSecretPassword!");
        login.clickLogin();

        SecureAreaPage secure = new SecureAreaPage(driver);
        String message = secure.getSuccessMessage();

        log.info("Success message received: " + message);

        Assert.assertTrue(message.contains("You logged into a secure area"),
                "Login success message not found.");

        log.info("Valid login test completed");
    }
}
