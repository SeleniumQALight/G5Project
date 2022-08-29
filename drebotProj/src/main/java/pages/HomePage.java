package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

//    public boolean isButtonSignOutDisplayed() {
//        try {
//            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[@class='btn btn-sm btn-secondary']"));
//            return buttonSignOut.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO checkURL
        Assert.assertTrue("HomePage doesn't loaded", headerElement.isButtonSignOutDisplayed());//,isButtonSignOutDisplayed());
        return this;
    }


    public HomePage openHomePage() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithValidCredential();
        checkIsRedirectToHomePage();

        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
