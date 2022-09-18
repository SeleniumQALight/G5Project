package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithParams extends BaseTest {
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";
    final static String SHORT_PASSWORD = "123";

    @Test
    @Parameters({
            TestData.VALID_LOGIN + COMMA + SHORT_PASSWORD
            , SHORT_USER_NAME + COMMA + TestData.VALID_PASSWORD
            , SHORT_USER_NAME + COMMA + SHORT_PASSWORD
//            ,TestData.VALID_LOGIN + COMMA + TestData.VALID_PASSWORD
    })
    @TestCaseName("loginError : login = {0}, password = {1}")
    public void loginError(String userName, String password) {
        loginPage
                .openLoginPage()
                .enterUserNameIntoLoginInput(userName)
                .enterPasswordIntoPasswordInput(password)
                .clickOnButtonLogIn()
                .checkInvalidLoginMessage()
        ;
    }
}