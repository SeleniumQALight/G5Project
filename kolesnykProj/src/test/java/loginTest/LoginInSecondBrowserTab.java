package loginTest;

import Pages.elements.HeaderElements;
import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginInSecondBrowserTab extends BaseTest {

    @Test
    public void checkUserIsSignInWhenSecondBrowserTabOpened(){
        loginPage
                .loginWithValidCredentials()
                .userOpensNewTab();
        loginPage.openLoginPage();
        Assert.assertTrue("Sigh Out button is not displayed",homePage.getHeaderElements().isButtonSignOutDisplayed());

    }
}
