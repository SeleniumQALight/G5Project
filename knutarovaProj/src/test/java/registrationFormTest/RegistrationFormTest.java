package registrationFormTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationFormTest extends BaseTest {
    @Test
    public void validationMessagesInRegistrationForm(){
        loginPage
                .openLoginPage()
                .enterTextInInputUsername("tr")
                .enterTextInInputEmail("test.com")
                .enterTextInInputPassword("123")
                .checkWeSeeThreeValidationMessagesInRegistrationForm()
                .checkErrorMessageDisplayed("Username must be at least 3 characters.")
                .checkErrorMessageDisplayed("You must provide a valid email address.")
                .checkErrorMessageDisplayed("Password must be at least 12 characters.")
        ;
    }
}
