package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

//@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    final static String COMMA = ",";
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
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

    }

    @Test
    public void validLoginUsingKeyboard() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        loginPage.usersPressesKeyTabTime(1);
        loginPage.isPasswordActive();
        loginPage.enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        loginPage.usersPressesKeyEnterTime(1);

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

    }
    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData
                (CommonActionsWithElements.configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoPasswordInput(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

    }

//    @Test
//    @Parameters
//            ({
//                    TestData.VALID_LOGIN+COMMA+"123"
//                    ,"tr"+COMMA+TestData.VALID_PASSWORD
//                    ,"tr"+COMMA+"123"
//            })
//    @TestCaseName("login errors: login{0}, password{1}")
//    public void invalidLogin(String username, String password) {
//        loginPage.openLoginPage();
//        loginPage.enterUserNameIntoLoginInput(username);
//        loginPage.enterPasswordIntoPasswordInput(password);
//        loginPage.clickOnButtonLogin();
//
//        Assert.assertFalse("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());
//        Assert.assertTrue("Invalid login message is not visible", loginPage.isInvalidLoginMsgVisible());
//
//    }
}
