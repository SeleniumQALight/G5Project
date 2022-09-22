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

import static Pages.CommonActionWithElements.configProperties;

@RunWith(Parameterized.class)
public class RegistrationTestWithExcel extends BaseTest {
    String userName, email, password,  expectedError;

    public RegistrationTestWithExcel(String userName, String email, String password, String expectedError) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.expectedError = expectedError;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "registrationErrors").getData();
    }

    @Test
    public void registrationErrors(){

        loginPage
                .openLoginPage()
                .fillInUserNameRegister(userName)
                .fillInUserEmailRegister(email)
                .fillInUserPasswordRegister(password)
                .checkErrorMessages(expectedError)
                ;
    }
}
