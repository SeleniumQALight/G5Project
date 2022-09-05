package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    final String[] alertTextArray = {"Username must be at least 3 characters.",
            "You must provide a valid email address.",
            "Password must be at least 12 characters."};

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

        Assert.assertFalse("Button SignOut is Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

        Assert.assertTrue("Message: 'Invalid username / password'  was invisible", loginPage.isMessageInvalidUserPassword());
    }

    @Test
    public void checkAlertInRegistration() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistration("tr");
        loginPage.enterEmailIntoRegistration("test.com");
        loginPage.enterPasswordIntoRegistration("123");
        loginPage.clickOnSignUpForOurApp();

        Assert.assertFalse("Button SignOut is Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

        loginPage.checkCountAlertMessage(alertTextArray);
        loginPage.checkAlertText(alertTextArray);
    }
}
