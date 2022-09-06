package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationFormTest extends BaseTest {
    @Test
    public void validationMessages (){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationInput("tr");
        loginPage.enterEmailIntoRegistrationInput("test.com");
        loginPage.enterPasswordIntoRegistrationInput("123");
        loginPage.checkValidationMessagesNumber(3);
        loginPage.checkValidationMessageText();

    }
}
