package postTests;

import baseTest.BaseTest;
import libs.*;
import org.junit.After;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CreatePostWithExcelAndDBTest extends BaseTest {
    Map<String, String> dataFromExcel = ExcelDriver.getData(CommonActionsWithElements.configProperties.DATA_FILE(), "createPost");
    final String TITLE = dataFromExcel.get("title") + Util.getDateAndTimeFormatted();

    public CreatePostWithExcelAndDBTest() throws IOException {
    }

//    private Database mysqlDB;
    final String LOGIN = "newqaauto";
//
//    @Before
//    public void setUp() throws SQLException, ClassNotFoundException {
//        //mysqlDB = MySQL_Database.getDataBase();
//    }

    @Test
    public void createPostWithExcelAndDB() throws SQLException, ClassNotFoundException {
        //mysqlDB = MySQL_Database.getDataBase();

//        List<Map<String, String>> dataFromSeleniumTable = mysqlDB.selectTableAsMap(
//                String.format("Select * from seleniumUsers where login='%s'", LOGIN));
//        System.out.println(dataFromSeleniumTable);
//        System.out.println(dataFromSeleniumTable.get(0).get("login"));
//        System.out.println(dataFromSeleniumTable.get(0).get("password"));
        //mysqlDB.quit();

        DB_Util_seleniumUsers db_util = new DB_Util_seleniumUsers();

        loginPage.openLoginPage()
                //.enterUserNameIntoLoginInput(dataFromSeleniumTable.get(0).get("login"))
                //.enterPasswordIntoPasswordInput(dataFromSeleniumTable.get(0).get("password"))
                .enterUserNameIntoLoginInput(LOGIN)
                .enterPasswordIntoPasswordInput(db_util.getPassForLogin(LOGIN))
                .clickOnButtonLogIn()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle(TITLE)
                .enterTextInputBodyContent(dataFromExcel.get("body"))
                .putCheckBoxIntoRequiredCondition(dataFromExcel.get("checkBox"))
                .selectTextInDropDownRole(dataFromExcel.get("dropDown"))
                .clickSaveNewPost()
                .checkIsRedirectToPostInfoPage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @After
    public void postCondition() {
        homePage.openHomePage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);

        //mysqlDB.quit();

    }
}
