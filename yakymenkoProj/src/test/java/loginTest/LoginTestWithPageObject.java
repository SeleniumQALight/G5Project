package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
//        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
//        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonLogIn();

//        Assert.assertTrue("Button SignOut is not Displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignOut is not Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginAndInvalidPassword() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty1");
        loginPage.clickOnButtonLogIn();

//        Assert.assertTrue("Alert 'Invalid username/password' is not displayed ", homePage.isAlertDisplayed());
        Assert.assertTrue("Alert 'Invalid username/password' is not displayed ", loginPage.isAlertDisplayed() & loginPage.isButtonSignInDisplayed());
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(
                configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoPasswordInput(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SignOut is not Displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
}
