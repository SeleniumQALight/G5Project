package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SIgn Out is not displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
    public void invalidLoginOrPassword(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto111");
        loginPage.enterPasswordIntoInputPassword("123456qwerty111");
        loginPage.clickOnButtonLogIn();

        Assert.assertFalse("Button Sign Out is displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Button Sign In is not displayed", loginPage.isButtonSignInDisplayed());
        Assert.assertTrue("Invalid login or password", loginPage.isLoginOrPassInvalid() );

    }

}
