package postTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.*;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CreatePostTestWithPasswordFromDataBase extends BaseTest {

    private HomePage homepage;
    private WebDriver webDriver;


    @Test
    @Category(SmokeTestFilter.class)
    public void TC_1_createNewPostWithPasswordFromDataBase() throws SQLException, ClassNotFoundException, IOException {
        loginPage
                .openLoginPage()
                .enterUserNameIntoLoginInput("newqaauto");
        DB_Util db_util=new DB_Util();//for sel.user
        String pass=db_util.getPassForLoginFromSeleniumUsers("newqaauto");
        loginPage.enterPasswordIntoLoginInput(pass);
        loginPage.clickOnButtonSignIn();

    Map<String,String> dataForCreateNewPost=ExcelDriver.getData(".//src//main//java//data//testData.xls"
            ,"createPost");

        homePage
                        .getHeaderElement().clickOnButtonCreatePost()
                        .checkIsRedirectToCreatePostPage()
                        .selectDropdownElementsUI()
                        .enterTextInInputTitle(dataForCreateNewPost.get("title"))
                        .enterTextInInputBody(dataForCreateNewPost.get("body"))

                        .selectValueInDropDownRole(dataForCreateNewPost.get("dropdown"))
                        .clickOnSavePostButton()
                        .checkIsRedirectToPostPage()
                        .checkTextInAlert("New post successfully created.")
                        .getHeaderElement().clickOnMyProfileButton()
                        .checkIsRedirectToMyProfilePage()
                        .checkPostWasCreated(dataForCreateNewPost.get("title"));
            }
    @After
    public void deletePosts() throws IOException {
        Map<String,String> dataForCreateNewPost=ExcelDriver.getData(".//src//main//java//data//testData.xls"
                ,"createPost");
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresence(dataForCreateNewPost.get("title"));

    }
}



