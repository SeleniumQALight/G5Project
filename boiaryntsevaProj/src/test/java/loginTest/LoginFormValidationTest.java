package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginFormValidationTest extends BaseTest {

    @Test

    public void validateSignUpFormErrorMessagesCountAndText() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationInput("tr")
                .enterEmailIntoRegistrationInput("t.com")
                .enterUserPasswordIntoRegistrationInput("123")
                .validateErrorMessagesCountOnLoginPage(3)
                .validateErrorMessagesTextOnSignUp();
    }

}
