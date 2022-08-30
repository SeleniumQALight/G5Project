package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private final HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO checkURL
        Assert.assertTrue("HomePage does not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }

    public LoginPage checkIsRedirectToHomePageDoesNotHappened() {
        Assert.assertFalse("HomePage was loaded", headerElement.isButtonSignOutDisplayed());
        return new LoginPage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithValidCred();
        checkIsRedirectToHomePage();
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
