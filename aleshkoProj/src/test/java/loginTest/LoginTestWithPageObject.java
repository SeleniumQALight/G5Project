package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

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
    @Parameters({
            "qaauto,qwerty654321",
            "test,123456qwerty",
            "qaautoqa,qwe123456rty"
    })
    @TestCaseName("Invalid login: login = {0}, password = {1}")
    public void invalidLogin(String login, String password) {
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput(login)
                .enterPasswordIntoPasswordInput(password)
                .clickOnSignInButton()
                .checkIsRedirectToHomePageDoesNotHappened()
                .checkInlavidLoginAction();
    }
}
