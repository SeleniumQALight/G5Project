package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    final static String COMMA = ",";


    @Test

    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(
                configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button Sign Out is not Displayed"
                , homePage.getHeaderElement().isButtonSignOutDisplayed());

    }


    @Test
    @Parameters({
            "qaauto123"         +COMMA+TestData.VALID_PASSWORD,
            TestData.VALID_LOGIN+COMMA+"123",
            "qaauto123"         +COMMA+"123"
    })
    public void invalidLoginOrPassword(String userName, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(userName);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogIn();

        Assert.assertFalse("Button Sign Out is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button Sign In is not displayed", loginPage.isButtonSignInDisplayed());
        Assert.assertTrue("Invalid login or password", loginPage.isLoginOrPassInvalid() );

    }

}
