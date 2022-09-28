package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;

public class LoginValidWithTabButtonTest extends BaseTest {

    @Test
    public void LoginUsingTabToSwitchBetweenFields() throws InterruptedException {
        loginPage
                .openLoginPage()
                .enterUserNameIntoLoginInput(TestData.VALID_LOGIN)
                .usersPressesKeyTabTime(1);

        loginPage.checkPasswordFieldIsActive();
        loginPage.fillInPasswordField(TestData.VALID_PASSWORD)
                .pressEnterToSubmit();
        Assert.assertTrue("User is not logged in", homePage.getHeaderElements().isButtonSignOutDisplayed());
    }
}
