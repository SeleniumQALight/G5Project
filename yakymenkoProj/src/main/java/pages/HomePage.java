package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage { // Alt + Insert
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAlertDisplayed() {
        try {
            WebElement alertInvalidUsernamePassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            return alertInvalidUsernamePassword.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
