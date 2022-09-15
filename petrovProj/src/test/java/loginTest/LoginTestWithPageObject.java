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
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button Sign Out is not visible", homePage.isButtonSignOutDisplayed());
    }
/*
    @Test
    public void inValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto1");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Error message is missing", loginPage.isMessageErrorLoginOrPassword());

    }

    @Test
    public void inValidPassword(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty1");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Error message is missing", loginPage.isMessageErrorLoginOrPassword());

    }
*/



}