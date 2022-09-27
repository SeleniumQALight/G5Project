package loginTest;

import static pages.CommonActionsWithElements.configProperties;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;


public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.getHeaderElement().isButtonSighOutDisplayed());

    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(
                configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.getHeaderElement().isButtonSighOutDisplayed());

    }
}
