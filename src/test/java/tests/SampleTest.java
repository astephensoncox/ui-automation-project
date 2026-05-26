package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.DriverFactory;
import org.openqa.selenium.WebDriver;

public class SampleTest {

    @Test
    public void openGoogle() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
