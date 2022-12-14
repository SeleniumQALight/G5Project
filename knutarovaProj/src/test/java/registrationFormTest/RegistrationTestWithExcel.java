package registrationFormTest;

import baseTest.BaseTest;
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
    String username, email, password, expectedErrors;

    public RegistrationTestWithExcel(String username, String email, String password, String expectedErrors) {
        this.username = username;
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
    public void registrationErrors(){
        loginPage
                .openLoginPage()
                .enterTextInInputUsername(username)
                .enterTextInInputEmail(email)
                .enterTextInInputPassword(password)
                .checkErrorsMessages(expectedErrors)
        ;
    }
}
