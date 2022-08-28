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
        Assert.assertTrue("Button SignOut is not Displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("12345qwerty");
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Text is not found", homePage.isTextDisplayed());


    }

}
