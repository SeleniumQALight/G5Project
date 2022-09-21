package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    final static String VALID_LOGIN = "qaauto";
    final static String VALID_PASSWORD = "123456qwerty";
    final static String INVALID_LOGIN = "pockemon";
    final static String INVALID_PASSWORD = "pockiTest";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";

    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Button SignOut is not Displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Button SignOut is not Displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
    @Parameters ({ VALID_LOGIN + COMMA + INVALID_PASSWORD,
            INVALID_LOGIN + COMMA + VALID_PASSWORD,
            INVALID_LOGIN + COMMA + INVALID_PASSWORD

    })
    @TestCaseName ("invalidLogin: email {0}, password {1}" )
    public void invalidLogin(String login, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(login);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Text is not found", homePage.isTextDisplayed());


    }

}
