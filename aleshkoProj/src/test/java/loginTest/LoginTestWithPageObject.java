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
}
