package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import pages.elements.HeaderElement;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        loginPage.enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SignOut is not Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());//homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto111");
        loginPage.enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonLogIn();

        //Assert.assertFalse("Button SignOut is Displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertFalse("Button SignOut is Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

        Assert.assertTrue("Message: 'Invalid username / password'  was invisible", loginPage.isMessageInvalidUserPassword());
    }
}
