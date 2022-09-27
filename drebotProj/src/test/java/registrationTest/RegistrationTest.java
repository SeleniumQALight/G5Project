package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
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
    final String[] alertTextArray = {"Username must be at least 3 characters.",
            "You must provide a valid email address.",
            "Password must be at least 12 characters."};
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    //String expectedErrors = ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD;

    @Test
    @Parameters({
            SHORT_USER_NAME + COMMA + "test.com" + COMMA + "123" + COMMA + ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD
            , TestData.VALID_LOGIN + COMMA + "qqq" + COMMA + "123" + COMMA + ERROR_ALREADY_EXIST + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD
            , "q  a" + COMMA + "text@test.com" + COMMA + "123" + COMMA + ERROR_USERNAME + SEMICOLON + ERROR_PASSWORD
            , " " + COMMA + " " + COMMA + " " + COMMA + ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD
    })
    @TestCaseName("registrationErrors : login ={0}, email = {1}, password = {2}")
    public void registrationErrors(String userName, String email, String password, String expectedErrors) {
        loginPage.openLoginPage()
                .enterUserNameIntoRegistration(userName)
                .enterEmailIntoRegistration(email)
                .enterPasswordIntoRegistration(password)
                .checkErrorMessages(expectedErrors)
        ;
    }

    @Test
    public void registrationErrorsUsingTab() {
        loginPage.openLoginPage().enterUserNameIntoRegistrationUsingKey("tr")
                .usersPressesKeyTabTime(1);
        loginPage.enterEmailIntoRegistrationUsingKey("test.com")
                .usersPressesKeyTabTime(1);
        loginPage.enterPasswordIntoRegistrationUsingKey("123");

        loginPage.checkCountAlertMessage(alertTextArray);
        loginPage.checkAlertText(alertTextArray);
    }
}
