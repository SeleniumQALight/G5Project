package loginTest;

import static pages.CommonActionsWithElements.configProperties;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import libs.ExcelDriver;

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
    public void validLogin(){
        loginPage.openLoginPage()
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwert");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.getHeaderElement().isButtonSighOutDisplayed());

    }

    @Test
//    @Ignore
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
