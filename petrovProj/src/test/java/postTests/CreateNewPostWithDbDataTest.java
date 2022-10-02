package postTests;

import baseTest.BaseTest;
import libs.DB_Util_V2;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;


public class CreateNewPostWithDbDataTest extends BaseTest {
    String login, title, body, check, dropRole;
    DB_Util_V2 db_util_v2 = new DB_Util_V2();

    public CreateNewPostWithDbDataTest() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(
                configProperties.DATA_FILE(),"validLogInDB");
        this.login = dataForValidLogin.get("login");
        this.title = dataForValidLogin.get("title");
        this.body = dataForValidLogin.get("body");
        this.check = dataForValidLogin.get("check");
        this.dropRole = dataForValidLogin.get("dropRole");
    }

    @Before
    public void logIn() throws SQLException, ClassNotFoundException{

        loginPage.openLoginPage()
                .enterUserNameIntoLoginInput(login)
                .enterPasswordIntoInputPassword(db_util_v2.getPassForLogin(login))
                .clickOnButtonLogin()
        ;
    }

    @Test
    public void createPostWithDBData(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .fillInputCreatePostForm(title, body, check, dropRole)
                .clickOnCreatePostButton()
                .checkIsRedirectToPostPage()
                .checkIsAppliedCheckBox(TestData.PP_CHECK_MESSAGE)
                .checkTestIsAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(title)
        ;
    }

    @After
    public void DeletePosts(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(title)
        ;
    }
}
