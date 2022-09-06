package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignUpValidationMessages extends BaseTest {

    @Test
    public void checkValidationMessages(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoTheRegisterForm("tr")
                .enterEmailIntoTheRegisterForm("test.com")
                .enterPasswordIntoTheRegisterForm("123")
                .clickOnTheSignUpButton()
                .checkNumberOfValidationErrorMessages()
                .checkTextOnError("Username must be at least 3 characters.")
                .checkTextOnError("You must provide a valid email address.")
                .checkTextOnError("Password must be at least 12 characters.")
        ;
    }


}
