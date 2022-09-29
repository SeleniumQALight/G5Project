package registrationTest;

import baseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@RunWith(Parameterized.class)

public class RegistrationTestWithExel extends BaseTest {
    String userName,email,password, expectedErrors;

    public RegistrationTestWithExel(String userName, String email, String password, String expectedErrors) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }
    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(".//src//main//java//data//" + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "registrationErrors").getData();
    }

    @Test
    public void registrationErrors(){
        loginPage
                .openLoginPage()
                .enterUsernameIntoRegistrationForm(userName)
                .enterEmailIntoRegistrationForm(email)
                .enterPasswordIntoRegistrationForm(password)
                .checkErrorsMessage(expectedErrors);

    }
}
