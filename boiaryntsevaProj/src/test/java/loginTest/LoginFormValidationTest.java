package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginFormValidationTest extends BaseTest {

    @Test

    public void validateSignUpFormErrorMessagesCount(){
        loginPage.validateErrorMessagesCountOnLoginPage();
    }

    @Test

    public void validateSignUpFormErrorMessagesText(){
        loginPage.validateErrorMessagesTextOnSignUp();
    }
}
