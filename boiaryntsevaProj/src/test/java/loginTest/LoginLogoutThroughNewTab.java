package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

public class LoginLogoutThroughNewTab extends BaseTest {


    @Test
    public void loggedInUserRemainsLoggedInInNewTab() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

        loginPage.userOpensNewTab();
        loginPage.openLoginPage();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());
        Assert.assertEquals("Different name is displayed", TestData.VALID_LOGIN, loginPage.getTextLoggedInUserNameAsString());

    }

    @Test
    public void loggedOutUserRemainsLoggedOutIneNewTab() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoPasswordInput("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign out button is not displayed", headerElement.isButtonSignedOutDisplayed());

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
