package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestValidationMessage extends BaseTest {

    @Test
    public void checkValidationMessageOnLoginPage(){

        loginPage
                .registerNewUserWithCredential("ro", "fakeEmail", "fakePass")
                .checkAmountOfAlertDuringRegistration()
                .checkAlertsText("Username must be at least 3 characters.")
                .checkAlertsText("You must provide a valid email address.")
                .checkAlertsText("Password must be at least 12 characters.");


    }
}
