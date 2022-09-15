package registrationFormTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class TestForLogInThroughTheHeaderForm extends BaseTest {
    final static String COMMA = ",";

    @Test
    @Parameters({
            TestData.VALID_LOGIN + COMMA + "000",
            "QA"                 + COMMA + TestData.VALID_PASSWORD
    })
    @TestCaseName("registrationErrors: login={0}, password={1}")
public void enterHeaderErrors(String username, String password){
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput(username)
                .enterPasswordIntoInputPassword(password)
                .clickOnButtonLogIn()
                .checkErrorMessageForInvalidHeaderUsernamePassword()
        ;
    }
}
