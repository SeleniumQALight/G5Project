package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage{

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
    public boolean isButtonSingOutDisplayed(){
        try {
            WebElement buttonSingout = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingout.isDisplayed();

        }catch (Exception e){
            return false;
        }
    }
}
