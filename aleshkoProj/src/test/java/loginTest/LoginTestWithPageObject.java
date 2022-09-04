package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput("qaauto")
                .enterPasswordIntoPasswordInput("123456qwerty")
                .clickOnSignInButton()
                .checkIsRedirectToHomePage();
    }

    @Test
    public void invalidLogin() {
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput("qaauto")
                .enterPasswordIntoPasswordInput("654321qwerty")
                .clickOnSignInButton()
                .checkIsRedirectToHomePageDoesNotHappened()
                .checkInlavidLoginAction();
    }

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
}
