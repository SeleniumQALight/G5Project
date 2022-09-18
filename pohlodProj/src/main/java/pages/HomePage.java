package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            isElementDisplayed(buttonSignOut);
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

    /**
     * Perevirka 4y zavantazhylas HomePage
     * @return
     */
    public HomePage checkIsRedirectToHomePage(){
        checkUrl();
        Assert.assertTrue("HomePage does not loaded", isButtonSignOutDisplayed());
        return this;
    }

    /**
     * Metod jakyj vidkryvaje HomePage i pereviriaje, w4o same vona vidcrylas
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!isElementDisplayed(buttonSignOut)) {
            loginPage.loginWithValidCredWithoutOpenPage();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    /**
     * Viddaje pryvatnyj element Header
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }


}
