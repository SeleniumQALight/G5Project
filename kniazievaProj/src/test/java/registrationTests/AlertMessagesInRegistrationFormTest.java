package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;


public class AlertMessagesInRegistrationFormTest extends BaseTest {

    @Test
    public void validDataInFieldsInRegistrationForm(){
        loginPage.openLoginPage();
        loginPage.enterUsernameInRegistrationForm("tr");
        loginPage.enterEmailInRegistrationForm("test.com");
        loginPage.enterPasswordInRegistrationForm("123");
        loginPage.checkNumberOfMessageInRegistrationForm(3);
        loginPage.checkMessageDisplayedWithText("Username must be at least 3 characters.");
        loginPage.checkMessageDisplayedWithText("You must provide a valid email address.");
        loginPage.checkMessageDisplayedWithText("Password must be at least 12 characters.");

    }
}
