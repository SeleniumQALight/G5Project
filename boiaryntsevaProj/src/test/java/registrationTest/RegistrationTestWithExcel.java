package registrationTest;

import baseTest.BaseTest;
import junitparams.naming.TestCaseName;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.CommonActionsWithElements.configProperties;

@RunWith(Parameterized.class)
public class RegistrationTestWithExcel extends BaseTest {
    String userName, email,  password, expectedErrors;

    public RegistrationTestWithExcel(String userName, String email, String password, String expectedErrors) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }
    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "registrationErrors").getData();
    }

    @Test
    @TestCaseName("registrationErrors: login = {0}, email ={1}, password={2}")
    public void registrationErrors() {
        loginPage.openLoginPage()
                .enterUserNameIntoRegistrationInput(userName)
                .enterEmailIntoRegistrationInput(email)
                .enterUserPasswordIntoRegistrationInput(password)
                .checkErrorMessages(expectedErrors);

    }
}


