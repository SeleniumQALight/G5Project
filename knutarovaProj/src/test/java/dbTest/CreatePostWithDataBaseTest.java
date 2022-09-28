package dbTest;

import baseTest.BaseTest;
import libs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithDataBaseTest extends BaseTest {
    private Database mysqlDB;
    DB_Util_SeleniumUser_Table db_util_seleniumUser_table = new DB_Util_SeleniumUser_Table();
    Map<String, String> dataForHWTest = ExcelDriver.getData(configProperties.DATA_FILE(), "validDataForHW");
    final String TITLE = dataForHWTest.get("title");

    public CreatePostWithDataBaseTest() throws IOException {
    }

    @Before
    public void setUpDB() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
    }

    @Test
    public void TC4_createNewPostWithDataBase() throws SQLException, ClassNotFoundException, IOException {
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput("newqaauto")
                .enterPasswordIntoInputPassword(db_util_seleniumUser_table.getPasswordForLogin("newqaauto"))
                .clickOnButtonLogIn()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePotsPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody(dataForHWTest.get("body"))
                .setCheckConditionInCheckBox(dataForHWTest.get("checkBox"))
                .selectTextInDropDownByUI(dataForHWTest.get("dropDown"))
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @After
    public void deletePosts() throws SQLException {
        mysqlDB.quit();
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE)
        ;
    }

}
