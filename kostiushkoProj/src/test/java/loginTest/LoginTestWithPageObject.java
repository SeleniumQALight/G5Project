package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogininInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();
        homePage.isButtonSignOutDisplayed();

    }
    @Test
    public void noValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogininInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("NoValidPassword");
        loginPage.clickOnButtonLogIn();
        loginPage.isTextNoLogginDisplayed();

    }

}
