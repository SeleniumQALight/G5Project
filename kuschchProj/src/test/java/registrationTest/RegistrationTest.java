package registrationTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import libs.Util;

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

//    String expectedErrors = ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD;

    @Test
    @Parameters({
            SHORT_USER_NAME     +COMMA+"test.com"    +COMMA + "123"+COMMA + (ERROR_USERNAME+SEMICOLON+ERROR_EMAIL+SEMICOLON+ERROR_PASSWORD)
            , TestData.VALID_LOGIN+COMMA+"qqq"         +COMMA + "123"+COMMA + ERROR_ALREADY_EXIST+SEMICOLON+ERROR_EMAIL+SEMICOLON+ERROR_PASSWORD
            , SHORT_USER_NAME     +COMMA+"test@tex.com"+COMMA + "546"+COMMA + ERROR_USERNAME+SEMICOLON+ERROR_PASSWORD
            , ""                  +COMMA+"test@t\\ex.com"+COMMA + "546"+COMMA + ERROR_PASSWORD
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void TC1_registrationErrors(String userName, String email, String password, String expectedErrors){
        loginPage
                .openLoginPage()
                .enterUserNameIntoRegistrationForm(userName)
                .enterEmailIntoRegistrationFrom(email)
                .enterPasswordIntoRegistrationForm(password)
                .checkErrorsMessages(expectedErrors)

        ;

    }


    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void TC2_registrationErrors(String userName, String email, String password, String expectedErrors){
        loginPage
                .openLoginPage()
                .enterUserNameIntoRegistrationForm(userName)
                .enterEmailIntoRegistrationFrom(email)
                .enterPasswordIntoRegistrationForm(password)
                .checkErrorsMessages(expectedErrors)

        ;

    }

    final static String userDate = "user" + Util.getDateAndTimeFormattedOnlyNumbers();

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {userDate,"test@t\\ex.com", "546",ERROR_PASSWORD}
        };
    }
}
