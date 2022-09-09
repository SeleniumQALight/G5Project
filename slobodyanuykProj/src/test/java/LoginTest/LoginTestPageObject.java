package LoginTest;

import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestPageObject extends BaseTest {

    @Test
    public void start() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("Vadim");
        loginPage.enterPasswordIntoLoginInput("12Vadim121212");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());
        System.out.println("Start");
    }

}
