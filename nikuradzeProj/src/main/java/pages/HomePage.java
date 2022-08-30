package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

/*    public boolean isButtonSignOutDisplayed(){
        try{
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }*/

    /**
     * перевирка чи завантажилась HomePage
     * @return
     */
    public HomePage checkIsRedirectToHomePage(){
        //TODO checkURL
        Assert.assertTrue("HomePage is not loaded", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    /**
     * метод який видкривае HomePage и перевиряе що саме вона видкрилась
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithValidCred();
        checkIsRedirectToHomePage();
        return this;
    }

    /**
     * виддае приватний елемент Header
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
