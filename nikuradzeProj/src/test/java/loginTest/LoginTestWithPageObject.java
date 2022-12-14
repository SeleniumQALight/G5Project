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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
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
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        loginPage.enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }
    @Test
    @Ignore
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("qwerty789456");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button Sign In and alert message are not visible", loginPage.isButtonSignInDisplayed() & loginPage.isAlertInvalidLoginDisplayed());
    }
    @Test
    @Parameters({
            TestData.VALID_LOGIN + "," + "123"
            , "qalogin"          + "," + TestData.VALID_PASSWORD
            , "qainvalid"        + "," + "pass123"
    })
    @TestCaseName("loginError : login = {0}, password = {1}")
    public void invalidLoginWithParams(String userName, String password){
        loginPage
                .openLoginPage()
                .enterUserNameIntoLoginInput(userName)
                .enterPasswordIntoInputPassword(password)
                .clickOnButtonLogIn()
                .checkInvalidLogin()
        ;
    }
    @Test
    public void validLoginWithButtons(){
        loginPage
                .openLoginPage()
                .usersPressesKeyTabTime(2);
        loginPage
                .enterUserNameIntoLoginInputWithButtons(TestData.VALID_LOGIN)
                .usersPressesKeyTabTime(1);
        loginPage
                .enterPasswordIntoInputPasswordWithButtons(TestData.VALID_PASSWORD)
                .usersPressesKeyTabTime(1);
        loginPage
                .usersPressesKeyEnterTime(1);

        Assert.assertTrue("Button Sign Out is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }
    @Test
    public void userIsLoggedInNewTab(){
        homePage
                .openHomePage()
                .checkHomePageUserLoggedInNewTab()
                .getHeaderElement().clickOnSignOutButton()
                .logOutInBothTabs()
        ;
        Assert.assertTrue("Button Sign Out is not displayed", loginPage.isButtonSignInDisplayed());

    }
    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button Sign Out is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }
}
