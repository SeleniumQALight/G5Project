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

        loginPage.getMessageListWithXpath(userNameShort);
        loginPage.getMessageListWithXpath(noValidEmail);
        loginPage.getMessageListWithXpath(passwordShort);
        Assert.assertEquals("3",3, loginPage.countingTheNumberOfElements());
    }


    }


