package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginErrorPage;
import pages.LoginPage;

public class InvalidLoginTest extends BaseTest {


    @Test
    public void testInvalidLogin() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("wrongUser");
        login.enterPassword("wrongPass");
        login.clickLogin();

        LoginErrorPage error = new LoginErrorPage(driver);
        Assert.assertTrue(error.getErrorMessage().contains("Your username is invalid")
                || error.getErrorMessage().contains("Your password is invalid"));
    }

}
