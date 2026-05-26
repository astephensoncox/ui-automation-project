package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitUtils wait;
    protected Logger log = LogManager.getLogger(this.getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 10);
    }

    protected WebElement find(By locator) {
        log.info("Finding element: " + locator);
        return wait.waitForVisible(locator);
    }

    protected void click(By locator) {
        log.info("Clicking element: " + locator);
        wait.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        log.info("Typing into element: " + locator + " | Text: " + text);
        find(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        log.info("Getting text from element: " + locator);
        return find(locator).getText();
    }

}
