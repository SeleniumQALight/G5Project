package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginNewTab extends BaseTest {

    @Before
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

    }


    @Test
    public void loggedInUserRemainsLoggedInInNewTab(){
        loginPage.userOpensNewTab();
        loginPage.openLoginPage();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());
        Assert.assertEquals("Different name is displayed", TestData.VALID_LOGIN, loginPage.getTextLoggedInUserNameAsString());



    }
}
