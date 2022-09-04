package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationFormTest extends BaseTest {
    @Test
    public void validationMessages () throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationInput("tr");
        loginPage.enterEmailIntoRegistrationInput("test.com");
        loginPage.enterPasswordIntoRegistrationInput("123");
        loginPage.checkValidationMessagesNumber();
        loginPage.checkValidationMessageText();

    }
}
