package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
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
    public void validLoginInWindow() {
        loginPage.loginWithValidCredential()
                .checkIsRedirectToHomePage()
                .getHeaderElement().checkUserNameInHeader(TestData.VALID_LOGIN);
        homePage.openTheSameNewWindow()
                .checkIsRedirectToHomePage()
                .getHeaderElement().checkUserNameInHeader(TestData.VALID_LOGIN);

        homePage.checkLogOutInWindow();


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

        Assert.assertFalse("Button SignOut is Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

        loginPage.checkCountAlertMessage(alertTextArray);
        loginPage.checkAlertText(alertTextArray);
    }

    @Test
    @Parameters({"qw," + TestData.VALID_PASSWORD,
            TestData.VALID_LOGIN + ",qwe",
            "," + ""
    })
    @TestCaseName("loginErrors : login ={0}, password = {1}")
    public void invalidLoginCredentials(String login, String password) {
        loginPage.openLoginPage()
                .enterUserNameIntoLoginInput(login)
                .enterPasswordIntoPasswordInput(password)
                .clickOnButtonLogIn();

        Assert.assertFalse("Home page is loaded", homePage.getHeaderElement().isButtonSignOutDisplayed());

        Assert.assertTrue("Message 'Invalid username / password' isn't displayed", loginPage.isMessageInvalidUserPassword());
    }

    @Test
    public void validLoginUsingKeysTabEnter() {
        loginPage.openLoginPage()
                .enterLoginIntoLoginInputUsingKeyTab(TestData.VALID_LOGIN)
                .enterPasswordIntoPasswordInputUsingKeyTab(TestData.VALID_PASSWORD)
                .clickOnButtonLogInUsingKey()
                .getHeaderElement().checkUserNameInHeader(TestData.VALID_LOGIN)
        ;
        Assert.assertTrue("Button SignOut is Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }


}
