package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

//@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {

    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput("qaauto")
                .enterPasswordIntoPasswordInput("123456qwerty")
                .clickOnSignInButton()
                .checkIsRedirectToHomePage();
    }

    @Test
//    @Ignore
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData
                (configProperties.DATA_FILE(), "validLogOn");
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput(dataForValidLogin.get("login"))
                .enterPasswordIntoPasswordInput(dataForValidLogin.get("pass"))
                .clickOnSignInButton()
                .checkIsRedirectToHomePage();
    }

//    @Test
//    @Parameters({
//            "qaauto,qwerty654321",
//            "test,123456qwerty",
//            "qaautoqa,qwe123456rty"
//    })
//    @TestCaseName("Invalid login: login = {0}, password = {1}")
//    public void invalidLogin(String login, String password) {
//        loginPage
//                .openLoginPage()
//                .enterUsernameIntoLoginInput(login)
//                .enterPasswordIntoPasswordInput(password)
//                .clickOnSignInButton()
//                .checkIsRedirectToHomePageDoesNotHappened()
//                .checkInlavidLoginAction();
//    }
}
