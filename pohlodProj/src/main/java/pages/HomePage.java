package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isTextDisplayed() {
        try{
            WebElement invalidPasswordLoginMessage = webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
            return invalidPasswordLoginMessage.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}
