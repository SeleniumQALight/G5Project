package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    String expectedErrors = ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD;

    @Test
    public void invalidRegistrationData() {
        loginPage
                .openLoginPage()
                .enterUsernameIntoRegistrationInput("tr")
                .enterEmailIntoRegistrationInput("test.com")
                .enterPasswordIntoRegistrationInput("123")
                .checkAlertAboutUsernameOnSignUpForm("Username must be at least 3 characters.")
                .checkAlertAboutEmailOnSignUpForm("You must provide a valid email address.")
                .checkAlertAboutPasswordOnSignUpForm("Password must be at least 12 characters.");
    }

    @Test
    public void registrationErrors() {
        loginPage
                .openLoginPage()
                .enterUsernameIntoRegistrationInput(SHORT_USER_NAME)
                .enterEmailIntoRegistrationInput("test.com")
                .enterPasswordIntoRegistrationInput("123")
                .checkErrorsMessagesOnRegistrationForm(expectedErrors)
                ;
    }
}
