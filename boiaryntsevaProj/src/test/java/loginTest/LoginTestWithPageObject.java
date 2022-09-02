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
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("1123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertFalse("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());
        Assert.assertTrue("Invalid login message is not visible", loginPage.isInvalidLoginMsgVisible());

    }
}
