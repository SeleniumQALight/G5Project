package Pages;

import Pages.elements.HeaderElements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    private HeaderElements headerElements = new HeaderElements(driver);
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

    public HomePage checkIsRedirectToHomePage(){
        //TODO checkURL
        Assert.assertTrue("Home page is not opened", isButtonSignOutDisplayed());
        return this;
    }

    public HomePage openHomePage() {
        // action on Login page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        // check that home page open
        checkIsRedirectToHomePage();
        return this;
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }
}
