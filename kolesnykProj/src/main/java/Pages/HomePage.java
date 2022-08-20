package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            WebElement singOutButton = driver.findElement(By.xpath("//button[@class='btn btn-sm btn-secondary']"));
            return singOutButton.isDisplayed();
        } catch (Exception e) {
            System.out.println("Button 'Sight Out' is not found");
            return false;
        }
    }
}
