package registrationTest;

import org.junit.Test;

import baseTest.BaseTest;

public class RegistrationTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    String expectedErrors = ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD;


    @Test
    public void registrationErrors(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoRegistrationForm(SHORT_USER_NAME)
                .enterEmailIntoRegistrationFrom("test.com")
                .enterPasswordIntoRegistrationForm("123")
                .checkErrorsMessages(expectedErrors)

        ;

    }

}
