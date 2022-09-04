package LoginTest;
import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestPageObject  extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();
        Assert.assertTrue("Button Signout is not Displayed", homePage.isButtonSignOutDisplayed());

    }
}
