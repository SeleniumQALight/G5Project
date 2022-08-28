package Pages;

import Pages.elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {

        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e)

        {
            return false;
        }
    }
    public HomePage checkIsredirectToHomePage(){
        //TODO checkURL
        Assert.assertTrue("HomePage does not load",
                isButtonSignOutDisplayed());
        return this;
    }
            /** метод який відкриває хоум пейдж і перевіряє що саме вона відкрилась*/

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginwithValidCred();
        checkIsredirectToHomePage();
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    } /**віддає приватний елемент Header*/

    public void clickOnCreatePostButton() {
    }
}
