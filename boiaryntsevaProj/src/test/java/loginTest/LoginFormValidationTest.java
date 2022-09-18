package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginFormValidationTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
//    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
//    final static String ERROR_ALREADY_EXIST="That username is already taken.";

    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";
    String expectedErrors = ERROR_USERNAME + SEMICOLON + ERROR_PASSWORD;

    @Test

    public void validateSignUpFormErrorMessagesCountAndText() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationInput("tr")
                .enterEmailIntoRegistrationInput("t.com")
                .enterUserPasswordIntoRegistrationInput("123")
                .validateErrorMessagesCountOnLoginPage(3)
                .validateErrorMessagesTextOnSignUp();
    }

    @Test
    public void validateSignUpFormErrorMessagesCountAndTextViaKeyboard(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationInput("tr");
        loginPage.usersPressesKeyTabTime(2);
        loginPage.enterUserPasswordIntoRegistrationInput("123");
        loginPage.validateErrorMessagesCountOnLoginPage(2);
        loginPage.checkErrorMessages(expectedErrors);
    }

}
