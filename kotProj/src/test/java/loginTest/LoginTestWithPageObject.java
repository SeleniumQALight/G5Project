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
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button Sign out is not displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("Invalid");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Alert Invalid password does not appears", homePage.doesAlertInvalidPasswordAppear());
    }

}
