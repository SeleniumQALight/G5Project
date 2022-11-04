package loginTest;

import baseTest.BaseTestComplexApp;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithInValidParams extends BaseTestComplexApp {
    final static String ERROR_USERNAME_PASSWORD = "Invalid username / pasword";
    final static String COMMA = ",";


    @Test
    @Parameters({
            "User1234wdds" + COMMA + TestData.VALID_PASSWORD + COMMA + ERROR_USERNAME_PASSWORD,
            TestData.VALID_LOGIN + COMMA + "rtwnfhyer34523" + COMMA + ERROR_USERNAME_PASSWORD,
            "kshfbv566" + COMMA + "rtwnfh523" + COMMA + ERROR_USERNAME_PASSWORD,
            "" + COMMA + "" + COMMA + ERROR_USERNAME_PASSWORD

    })
    @TestCaseName("AuthorizationErrors : login = {0}, password = {1}")
    public void inValidLogin(String userLogin, String userPassword, String errorMessage){
        loginPage.openLoginPage()
        .enterUserNameIntoLoginInput(userLogin)
        .enterPasswordIntoInputPassword(userPassword)
        .clickOnButtonLogin();
        loginPage.checkAuthErrorsMessage(errorMessage);

    }
}
