package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("rosko48");
        loginPage.enterPasswordIntoInputPassword("12345678912345");
        loginPage.clickOnLoginButton();
        Assert.assertTrue("Button 'Sign Out' is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void loginWithWrongPassword(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("rosko48");
        loginPage.enterPasswordIntoInputPassword("wrong password");
        loginPage.clickOnLoginButton();
        Assert.assertTrue("User is logged in with incorrect password", loginPage.isButtonSignInDisplayed());
    }
}
