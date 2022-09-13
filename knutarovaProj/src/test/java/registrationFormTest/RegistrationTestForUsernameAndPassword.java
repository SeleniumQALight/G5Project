package registrationFormTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class RegistrationTestForUsernameAndPassword extends BaseTest {
    final static String VALID_USERNAME = "That username is already taken.";
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";

    @Test
    @Parameters({
            TestData.VALID_LOGIN + COMMA + "000"                   + COMMA + (VALID_USERNAME + SEMICOLON + ERROR_PASSWORD)
            , "QA"               + COMMA + TestData.VALID_PASSWORD + COMMA + (ERROR_USERNAME)
    })
    @TestCaseName("registrationErrors: login={0}, password={1}")
    public void registrationErrors(String username, String password, String expectedErrors) {
        loginPage
                .openLoginPage()
                .enterTextInInputUsername(username)
                .enterTextInInputPassword(password)
                .checkErrorsMessages(expectedErrors)
        ;
    }
}

