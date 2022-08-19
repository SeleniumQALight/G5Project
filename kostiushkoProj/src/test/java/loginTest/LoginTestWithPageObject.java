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

        Assert.assertTrue("Button SinOut is not Displayed ",
                homePage.isButtonSignOutDisplayed());


    }
}
