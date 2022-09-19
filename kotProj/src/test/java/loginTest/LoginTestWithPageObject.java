package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

   @RunWith(JUnitParamsRunner.class)

public class LoginTestWithPageObject extends BaseTest {
       final static String COMMA = ",";

       @Test
       public void validLogin() {
           loginPage.openLoginPage();
           loginPage.enterUsernameIntoLoginInput(TestData.VALID_LOGIN);
           loginPage.enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
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

       @Test
       @Parameters({
               TestData.INVALID_LOGIN + COMMA + TestData.VALID_PASSWORD,
               TestData.VALID_LOGIN + COMMA + TestData.INVALID_PASSWORD,
               TestData.INVALID_LOGIN + COMMA + TestData.INVALID_PASSWORD})


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