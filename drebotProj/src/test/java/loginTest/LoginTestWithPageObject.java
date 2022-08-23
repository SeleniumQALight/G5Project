package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SignOut is not Displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto111");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertFalse("Button SignOut is Displayed", homePage.isButtonSignOutDisplayed());

        Assert.assertTrue("Message: 'Invalid username / password'  was invisible", loginPage.isMessageInvalidUserPassword());
    }
}
