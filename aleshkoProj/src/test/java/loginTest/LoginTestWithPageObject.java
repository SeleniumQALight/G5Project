package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnSignInButton();

        Assert.assertTrue("Button Sign Out is not visible", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("654321qwerty");
        loginPage.clickOnSignInButton();

        Assert.assertTrue("Alert about wrong username/password is not visible", loginPage.isAlertDisplayed());
        Assert.assertTrue("Button \"Sign In\" is not visible", loginPage.isButtonSignInDisplayed());
    }
}
