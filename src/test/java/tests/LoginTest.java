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
        LoginPage login = new LoginPage(driver);
        login.enterUsername(ConfigReader.get("username"));
        login.enterPassword(ConfigReader.get("password"));
        login.clickLogin();

        SecureAreaPage secure = new SecureAreaPage(driver);
        Assert.assertTrue(secure.getSuccessMessage().contains("You logged into a secure area"));
    }
}