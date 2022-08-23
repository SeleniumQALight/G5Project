package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
    public  boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSingOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isTextNoLogginDisplayed() {
        try {
            WebElement textNoLoggin = webDriver.findElement(By.xpath(".//* [@class='alert alert-danger text-center']"));
            return textNoLoggin.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
