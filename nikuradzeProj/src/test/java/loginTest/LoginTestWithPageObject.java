package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        loginPage.enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }
    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("qwerty789456");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button Sign In and alert message are not visible", loginPage.isButtonSignInDisplayed() & loginPage.isAlertInvalidLoginDisplayed());
    }
}
