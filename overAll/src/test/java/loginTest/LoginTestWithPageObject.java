package loginTest;

import org.junit.Assert;
import org.junit.Test;

import baseTest.BaseTest;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.getHeaderElement().isButtonSighOutDisplayed());

    }
}
