package registrationTest;

import baseTest.BaseTestComplexApp;
import org.junit.Assert;
import org.junit.Test;

public class validMessageFromLoginPageTest extends BaseTestComplexApp {
    
    @Test
    public void validMessageFromLoginPage(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationForm("tr");
        loginPage.enterEmailIntoRegistrationForm("test.com");
        loginPage.enterPasswordIntoRegistrationForm("123");
        Assert.assertTrue("UserName Error message is missing", loginPage.isMessageErrorDisplayed("Username must be at least 3 characters."));
        Assert.assertTrue("UserEmail Error message is missing", loginPage.isMessageErrorDisplayed("You must provide a valid email address."));
        Assert.assertTrue("User Password Error message is missing", loginPage.isMessageErrorDisplayed("Password must be at least 12 characters."));
    }

    
}
