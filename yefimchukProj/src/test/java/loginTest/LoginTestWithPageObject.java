package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
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
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
     loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
    // @Ignore
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(
                configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Button Sign Out is not displayed", homePage.isButtonSignOutDisplayed());

    }
}
