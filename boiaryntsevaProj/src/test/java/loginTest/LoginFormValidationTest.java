package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginFormValidationTest extends BaseTest {

    @Test

    public void validateSignUpFormErrorMessagesCountAndText() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationInput("tr")
                .enterUserPasswordIntoRegistrationInput("123")
                .enterEmailIntoRegistrationInput("t.com")
                .validateErrorMessagesCountOnLoginPage()
                .validateErrorMessagesTextOnSignUp();
    }

}
