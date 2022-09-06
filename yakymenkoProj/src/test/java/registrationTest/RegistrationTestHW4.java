package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTestHW4 extends BaseTest {
    @Test
    public void checkValidationAlertsInRegistrationForm() {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoUsernameRegisterInput("tr");
        loginPage.enterEmailIntoEmailRegisterInput("test.com");
        loginPage.enterPasswordIntoPasswordRegisterInput("123");
        loginPage.checkValidationAlertNumber();
        loginPage.checkValidationAlertText();
    }
}