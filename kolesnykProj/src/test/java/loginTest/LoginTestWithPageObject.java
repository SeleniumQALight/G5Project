package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    final static String VALID_USERNAME = "rosko48";
    final static String INVALID_USERNAME = "ross";
    final static String VALID_PASSWORD = "12345678912345";
    final static String INVALID_PASSWORD = "12345";
    final static String COMMA = ",";


    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("rosko48");
        loginPage.enterPasswordIntoInputPassword("12345678912345");
        loginPage.clickOnLoginButton();
        Assert.assertTrue("Button 'Sign Out' is not displayed", homePage.getHeaderElements().isButtonSignOutDisplayed());
    }

    @Test
    @Parameters({
             VALID_USERNAME  +COMMA+ INVALID_PASSWORD
            ,INVALID_USERNAME+COMMA+ VALID_PASSWORD
            ,INVALID_USERNAME+COMMA+ INVALID_PASSWORD
    })
    @TestCaseName("InvalidLogin : userName = {0}, password = {1}")
    public void loginWithWrongPassword(String name, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(name);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertTrue("User is logged in with incorrect password", loginPage.isButtonSignInDisplayed());
    }
}
