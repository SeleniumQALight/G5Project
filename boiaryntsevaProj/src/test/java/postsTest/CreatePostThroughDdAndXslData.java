package postsTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.Selenium_Users;
import org.junit.After;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


public class CreatePostThroughDdAndXslData extends BaseTest {
    final String LOGIN = "newqaauto";

    Selenium_Users selenium_users = new Selenium_Users();
    final String PASS = selenium_users.getPasswordFromSeleniumUsers(LOGIN);

    public CreatePostThroughDdAndXslData() throws SQLException, ClassNotFoundException, IOException {
    }

    Map<String, String> dataForPost = ExcelDriver.getData
            (CommonActionsWithElements.configProperties.DATA_FILE_NEW(), "CreatePostTest");
    String title = String.format(dataForPost.get("title"), "boiaryntseva").trim();

    @Test
    public void createPostUsingDataFromDbAndXsl() throws IOException {


        loginPage.openLoginPage()
                .enterUserNameIntoLoginInput(LOGIN)
                .enterPasswordIntoPasswordInput(PASS)
                .clickOnButtonLogin()
                .getHeaderElement()
                .clickOnCreatePostButton()
                .enterTextInputTitle(title)
                .enterTextInputBody(dataForPost.get("body"))
                .selectValueForCheckBox(dataForPost.get("isPostUnique"))
                .selectValueInDropDownRole(dataForPost.get("valueForDropDown"))
                .savePost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.");

    }

    @After
    public void deletePost() {
        homePage.getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(title);
    }
}
