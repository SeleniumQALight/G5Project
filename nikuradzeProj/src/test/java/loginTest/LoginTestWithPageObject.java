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
    @Test
    @Parameters({
            TestData.VALID_LOGIN + "," + "123"
            , "qalogin"          + "," + TestData.VALID_PASSWORD
            , "qainvalid"        + "," + "pass123"
    })
    @TestCaseName("loginError : login = {0}, password = {1}")
    public void invalidLoginWithParams(String userName, String password){
        loginPage
                .openLoginPage()
                .enterUserNameIntoLoginInput(userName)
                .enterPasswordIntoInputPassword(password)
                .clickOnButtonLogIn()
                .checkInvalidLogin()
        ;
    }
    @Test
    public void validLoginWithButtons(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoLoginInputWithButtons(TestData.VALID_LOGIN)
                .enterPasswordIntoInputPasswordWithButtons(TestData.VALID_PASSWORD)
                .clickOnButtonLogInWithEnter()
        ;
        Assert.assertTrue("Button Sign Out is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }

}
