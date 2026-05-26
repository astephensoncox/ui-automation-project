package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginErrorPage extends BasePage {

    private By errorMessage = By.id("flash");

    public LoginErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
