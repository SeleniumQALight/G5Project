package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = new HomePage(webDriver);

        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnSignInButton();

        Assert.assertTrue("Button Sign Out is not visible", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("654321qwerty");
        loginPage.clickOnSignInButton();

        Assert.assertTrue("Alert about wrong username/password is not visible", loginPage.isAlertDisplayed());
        Assert.assertTrue("Button \"Sign In\" is not visible", loginPage.isButtonSignInDisplayed());
    }
}
