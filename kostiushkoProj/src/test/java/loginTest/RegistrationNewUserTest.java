package loginTest;


import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationNewUserTest extends BaseTest {
static String userNameShort = "Username must be at least 3 characters.";
    static String noValidEmail = "You must provide a valid email address.";
    static String passwordShort = "Password must be at least 12 characters.";
    @Test
    public void checkValidMessageWhenRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationUserNameField("tr");
        loginPage.enterEmailIntoRegistrationEmailField("test.com");
        loginPage.enterPasswordIntoRegistrationPasswordField("123");
        loginPage.checkMessageEquals(userNameShort);
        loginPage.checkMessageEquals(noValidEmail);
        loginPage.checkMessageEquals(passwordShort);
        Assert.assertEquals("The number of messages does not match the expected number",3, loginPage.countingTheNumberOfMessage());
    }


    }


