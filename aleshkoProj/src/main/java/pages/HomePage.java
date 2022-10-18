package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import java.sql.SQLException;

public class HomePage extends ParentPage {
    private final HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        waitChatToBeHide();
        Assert.assertTrue("HomePage does not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }

    public LoginPage checkIsRedirectToHomePageDoesNotHappened() {
        Assert.assertFalse("HomePage was loaded", headerElement.isButtonSignOutDisplayed());
        return new LoginPage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!getHeaderElement().isButtonSignOutDisplayed()) {
            loginPage.loginWithValidCredWithoutOpenPage();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage openHomePageWithDataFromDB() throws SQLException, ClassNotFoundException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!getHeaderElement().isButtonSignOutDisplayed()) {
            loginPage.loginWithDataFromDB();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
