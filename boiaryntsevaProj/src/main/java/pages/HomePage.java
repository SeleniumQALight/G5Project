package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {


    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HomePage checkIsRedirectToHomePage() {
        //TODO checkURL
        Assert.assertTrue("Home page is not loaded", headerElement.isButtonSignedOutDisplayed());
        return this;
    }

    public HomePage openHomePage() {
        //Login page
        if (!getHeaderElement().isButtonSignedOutDisplayed()) {
            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.loginWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;


    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
