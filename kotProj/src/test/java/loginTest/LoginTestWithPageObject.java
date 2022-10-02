package loginTest;

import Pages.CommonActionsWithElements;
import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static Pages.CommonActionsWithElements.configProperties;

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
       public void validLoginWithExcel() throws IOException {
           Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties
                   .DATA_FILE(), "validLogOn");

           loginPage.openLoginPage();
           loginPage.enterUsernameIntoLoginInput(dataForValidLogin.get("login"));
           loginPage.enterPasswordIntoPasswordInput(dataForValidLogin.get("pass"));
           loginPage.clickOnButtonLogIn();

           Assert.assertTrue("Button Sign out is not displayed", homePage.isButtonSignOutDisplayed());

       }

       @Test
       public void invalidLogin() {
           loginPage.openLoginPage();
           loginPage.enterUsernameIntoLoginInput("qaauto");
           loginPage.enterPasswordIntoPasswordInput("Invalid");
           loginPage.clickOnButtonLogIn();

           Assert.assertTrue("Alert Invalid password does not appears", homePage.doesAlertInvalidPasswordAppear());
       }

//       @Test
//       @Parameters({
//               TestData.INVALID_LOGIN + COMMA + TestData.VALID_PASSWORD,
//               TestData.VALID_LOGIN + COMMA + TestData.INVALID_PASSWORD,
//               TestData.INVALID_LOGIN + COMMA + TestData.INVALID_PASSWORD})


       @TestCaseName("InvalidLogin : login = {0}, password = {1}")
       public void loginErrors(String userName,String password) {
    loginPage
            .openLoginPage()
            .enterUsernameIntoLoginInput(userName)
            .enterPasswordIntoPasswordInput(password)
            .clickOnButtonLogIn()
            .checkLoginIsInvalid();

       }
   }