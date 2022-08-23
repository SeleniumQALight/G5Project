package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSingOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
