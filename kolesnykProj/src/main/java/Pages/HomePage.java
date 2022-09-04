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

    @FindBy(xpath = "//button[@class='btn btn-sm btn-secondary']")
        WebElement singOutButton;

//    public boolean isButtonSignOutDisplayed() {
//        return isElementDisplayed(singOutButton);
//    }

    public HomePage checkIsRedirectToHomePage(){
        //TODO checkURL
        Assert.assertTrue("Home page is not opened", headerElements.isButtonSignOutDisplayed());
        return this;
    }

//    public HomePage openHomePage() {
//        // action on Login page
//        if (getHeaderElements().isButtonSignInDisplayed()){
//            LoginPage loginPage = new LoginPage(driver);
//            loginPage.loginWithValidCredentials();
//        }
//        // check that home page open
//        checkIsRedirectToHomePage();
//        return this;
//    }

    public HomePage openHomePage() {
        if (!headerElements.isButtonSignOutDisplayed()) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginWithValidCredentials();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }
}
