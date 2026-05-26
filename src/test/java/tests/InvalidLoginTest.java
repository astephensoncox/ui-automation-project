package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginErrorPage;
import pages.LoginPage;
import utils.ConfigReader;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void testInvalidLogin() {
        driver.get(ConfigReader.get("baseUrl"));

        LoginPage login = new LoginPage(driver);
        login.enterUsername("wrongUser");
        login.enterPassword("wrongPass");
        login.clickLogin();

        LoginErrorPage error = new LoginErrorPage(driver);
        String message = error.getErrorMessage();

        Assert.assertTrue(message.contains("Your username is invalid")
                        || message.contains("Your password is invalid"),
                "Expected error message not found.");
    }
}
