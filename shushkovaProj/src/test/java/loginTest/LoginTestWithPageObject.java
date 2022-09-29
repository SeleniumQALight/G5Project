package loginTest;

import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import baseTest.BaseTest;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;
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
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoLoginInput("123456qwert");
        loginPage.clickOnButtonSignIn();
        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.getHeaderElement().isButtonSingOutDisplayed());

    }
    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoLoginInput("123456");
        loginPage.clickOnButtonSignIn();
        Assert.assertTrue("Message 'Invalid username / pasword' is displayed "
                , loginPage.isMessageInvalidCredsDisplayed());

    }
        @Test
        public void validLoginWithExel() throws IOException {
            Map<String ,String>  dataForValidLogin= ExcelDriver.getData(".//src//main//java//data//testData.xls","validLogOn");
            loginPage.openLoginPage();
            loginPage.enterUserNameIntoLoginInput(dataForValidLogin.get("login"));
            loginPage.enterPasswordIntoLoginInput(dataForValidLogin.get("pass"));
            loginPage.clickOnButtonSignIn();
            Assert.assertTrue("Button SinOut is not Displayed"
                    , homePage.getHeaderElement().isButtonSingOutDisplayed());

        }
}
