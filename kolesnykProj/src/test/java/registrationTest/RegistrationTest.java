package registrationTest;

import categories.SmokeTestFilter;
import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class RegistrationTest extends BaseTest {

    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    //String expectedError = ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD;
    @Test
    @Parameters({
            SHORT_USER_NAME+COMMA        + "fakeEmail"+COMMA     + "123"+COMMA +ERROR_USERNAME+SEMICOLON+ERROR_EMAIL+SEMICOLON+ERROR_PASSWORD
            , TestData.VALID_LOGIN+COMMA + "fakeEmail"+COMMA     + "123"+COMMA +ERROR_ALREADY_EXIST+SEMICOLON+ERROR_EMAIL+SEMICOLON+ERROR_PASSWORD
            , SHORT_USER_NAME+COMMA      + "text@tex.com"+COMMA  + "123"+COMMA +ERROR_USERNAME+SEMICOLON+ERROR_PASSWORD
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String userName, String email, String password, String expectedError){

        loginPage
                .openLoginPage()
                .fillInUserNameRegister(userName)
                .fillInUserEmailRegister(email)
                .fillInUserPasswordRegister(password)
                .checkErrorMessages(expectedError)
                ;
    }
}
