package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ExcelDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;


@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String VALIDLOGIN = "qaauto";
    final  static String NOVALIDLOGIN = "login";
    final  static String VALIDPASSWORD = "123456qwerty";
    final  static String NOVALIDPASSWORD = "password";
    final static String COMMA = ",";

    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogininInput(VALIDLOGIN);
        loginPage.enterPasswordIntoInputPassword(VALIDPASSWORD);
        loginPage.clickOnButtonLogIn();
        homePage.isButtonSignOutDisplayed();

    }


    @Test
    @Parameters({ VALIDLOGIN+ COMMA +NOVALIDPASSWORD
            ,   NOVALIDLOGIN+ COMMA +VALIDPASSWORD
            ,   NOVALIDLOGIN+ COMMA +NOVALIDPASSWORD

    })
    public void noValidLogin(String login, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogininInput(login);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogIn();
        loginPage.isTextNoLogginDisplayed();

    }


    @Test
    public void validLoginWithExel() throws IOException {
        Map< String, String> dataForValidLogin = ExcelDriver.getData(
                configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogininInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogIn();
        homePage.isButtonSignOutDisplayed();

    }

}
