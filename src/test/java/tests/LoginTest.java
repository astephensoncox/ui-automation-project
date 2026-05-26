package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import pages.SecureAreaPage;


public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        driver.get(ConfigReader.get("baseUrl"));

        LoginPage login = new LoginPage(driver);
        login.enterUsername("tomsmith");
        login.enterPassword("SuperSecretPassword!");
        login.clickLogin();

        SecureAreaPage secure = new SecureAreaPage(driver);
        String message = secure.getSuccessMessage();

        Assert.assertTrue(message.contains("You logged into a secure area"),
                "Login success message not found.");

    }
}
