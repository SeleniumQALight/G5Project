package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

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
//    @Ignore
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
