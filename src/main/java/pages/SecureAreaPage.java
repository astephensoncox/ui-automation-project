package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePage {

    private By successMessage = By.id("flash");

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
