package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

public class LoginValidWithTabButtonTest extends BaseTest {

    @Test
    public void LoginUsingTabToSwitchBetweenFields(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoLoginInput(TestData.VALID_LOGIN)
                // press tab
                
                //check that field active field is Password
                .fillInPasswordField(TestData.VALID_PASSWORD)
                .pressEnterToSubmit();
        Assert.assertTrue("User is not logged in", homePage.getHeaderElements().isButtonSignOutDisplayed());
    }
}
