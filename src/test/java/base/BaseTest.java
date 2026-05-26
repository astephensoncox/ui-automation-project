package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotUtils;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    // -------------------------
    // REPORT INITIALIZATION
    // -------------------------
    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
        log.info("ExtentReports initialized");
    }

    // -------------------------
    // DRIVER + TEST INITIALIZATION
    // -------------------------
    @BeforeMethod
    public void setUp(Method method) {
        log.info("Starting test: " + method.getName());

        // Start Extent test
        ExtentTest test = extent.createTest(method.getName());
        ExtentTestManager.setTest(test);

        // Initialize WebDriver
        driver = DriverFactory.getDriver();
        log.info("Driver initialized");

        // Load environment URL
        String url = ConfigReader.get("baseUrl");
        log.info("Navigating to: " + url);
        driver.get(url);
    }

    // -------------------------
    // TEARDOWN + REPORTING
    // -------------------------
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test FAILED: " + result.getName());

            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
            ExtentTestManager.getTest()
                    .fail("Test Failed")
                    .addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            log.info("Test PASSED: " + result.getName());
            ExtentTestManager.getTest().pass("Test Passed");
        } else {
            log.warn("Test SKIPPED: " + result.getName());
            ExtentTestManager.getTest().skip("Test Skipped");
        }

        log.info("Closing browser");
        DriverFactory.quitDriver();
    }

    // -------------------------
    // FLUSH REPORT
    // -------------------------
    @AfterSuite
    public void tearDownSuite() {
        log.info("Flushing ExtentReports");
        extent.flush();
    }
}
