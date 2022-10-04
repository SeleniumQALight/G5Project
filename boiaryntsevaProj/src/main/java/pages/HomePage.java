package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {


    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }


    @Step
    public HomePage checkIsRedirectToHomePage() {
        //TODO checkURL
        Assert.assertTrue("Home page is not loaded", headerElement.isButtonSignedOutDisplayed());
        return this;
    }

    @Step
    public HomePage openHomePage() {
        //Login page
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!getHeaderElement().isButtonSignedOutDisplayed()) {

            loginPage.loginWithValidCredWithoutOpenPage();
        }
        checkIsRedirectToHomePage();
        return this;


    }

    @Step
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
