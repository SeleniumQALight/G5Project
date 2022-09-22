package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.chrono.JapaneseChronology;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String COMMA = ",";

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

    }

    @Test
    public void validLoginUsingKeyboard() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        loginPage.usersPressesKeyTabTime(1);
        loginPage.isPasswordActive();
        loginPage.enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        loginPage.usersPressesKeyEnterTime(1);

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

    }

    @Test
    @Parameters
            ({
                    TestData.VALID_LOGIN + COMMA + "123"
                    , "tr" + COMMA + TestData.VALID_PASSWORD
                    , "tr" + COMMA + "123"
            })
    @TestCaseName("login errors: login{0}, password{1}")
    public void invalidLogin(String username, String password) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(username);
        loginPage.enterPasswordIntoPasswordInput(password);
        loginPage.clickOnButtonLogin();

        Assert.assertFalse("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());
        Assert.assertTrue("Invalid login message is not visible", loginPage.isInvalidLoginMsgVisible());

    }


}
