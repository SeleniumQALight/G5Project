package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    private HeaderElement headerElement = new HeaderElement(webDriver);
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text() = 'Sign Out' ]"));
            return buttonSignOut.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Перевірка чи завантажилась Home Page
     * @return
     */

    public HomePage checkIsRedirectToHomePage(){
        //TODO checkURL
        Assert.assertTrue("Home Page does not loaded", isButtonSignOutDisplayed());
        return this;
    }


    /**
     * Метод який відкриває HomePage
     * i перевіряє що саме вона відкрита
     * @return
     */


    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithValidCred();
        checkIsRedirectToHomePage();
        return this;
    }

    /**
     * Віддає приватний елемент Header
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
