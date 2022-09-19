package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class RegistrationTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

 //   String expectedErrors = ERROR_USERNAME+SEMICOLON+ERROR_EMAIL+SEMICOLON+ERROR_PASSWORD;

    @Test
    @Parameters({
        SHORT_USER_NAME+COMMA+"test.com"+COMMA+"123"+COMMA+(ERROR_USERNAME+SEMICOLON+ERROR_EMAIL+SEMICOLON+ERROR_PASSWORD)

    })
    public void registrationErrors(String userName,String email,String password,String expectedErrors){
        loginPage
                .openLoginPage()
                .enterUsernameIntoRegistrationForm(userName)
                .enterEmailIntoRegistrationForm(email)
                .enterPasswordIntoRegistrationForm(password)
                .checkErrorsMessage(expectedErrors)

        ;


    }
}
