package negativePasswordTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class NegativePasswordTestWithPageObject extends BaseTest {
    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty123456");
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Message 'Invalid username / pasword' is not visible", homePage.isAMessageInvalidUsernamePassword());
    }
}
