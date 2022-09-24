package loginTest;

import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import baseTest.BaseTest;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoLoginInput("123456qwerty");
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
