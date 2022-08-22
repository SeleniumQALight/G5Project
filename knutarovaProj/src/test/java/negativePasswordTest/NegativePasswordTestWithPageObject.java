package negativePasswordTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.ParentPage;

public class NegativePasswordTestWithPageObject extends BaseTest {
    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty123456");
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Message 'Invalid username / password' is not visible", homePage.isAMessageInvalidUsernamePassword());
    }
}
