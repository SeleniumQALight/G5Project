package loginTest;

import org.junit.Assert;
import org.junit.Test;

import baseTest.BaseTest;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoLoginInput("123456qwerty");
        loginPage.clickOnButtonSignIn();
        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.isButtonSingOutDisplayed());

    }
}
