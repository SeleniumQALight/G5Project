package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class InvalidRegistration extends BaseTest {
    @Test

    public void invalidCredentialsMessages (){
        loginPage.openLoginPage()
       .enterUserNameIntoIntoRegInput("tr")
       .enterEmailIntoRegInput("test")
       .enterPasswordIntoRegInput("123")
       .checkNumberOfErrorMessages(3)
       .checkTextInErrorMessages();
    }


}
