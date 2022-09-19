package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginLogoutThroughNewTab extends BaseTest {

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

    @Test
    public void loggedOutUserRemainsLoggedOutIneNewTab(){
        homePage.userOpensNewTab();
        loginPage.openLoginPage();
        loginPage.userSwitchesToParentWindow();
        homePage.getHeaderElement().clickOnSignOutButton();
        Assert.assertFalse("Sign out button is displayed", headerElement.isButtonSignedOutDisplayed());

        homePage.userSwitchesToNextWindowTab();
        homePage.refreshPage();
        Assert.assertFalse("Sign out button is displayed", headerElement.isButtonSignedOutDisplayed());

    }
}
