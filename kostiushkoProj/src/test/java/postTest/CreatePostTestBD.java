package postTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;


public class CreatePostTestBD extends BaseTest {
        Map<String, String> testDataForCreatePost= ExcelDriver.getData(configProperties.DATA_FILE(), "newPostCreated");
    public CreatePostTestBD() throws IOException {
    }
String title = testDataForCreatePost.get("title")+ Util.getDateAndTimeFormatted();



    @Test
    public void createPostWithDB() throws SQLException, ClassNotFoundException {
        homePage
                .openHomePageWithDataFromDB()
                .getHeaderElement().clickOnButtonCreatePost()
                .chekIsRedirectToCreatePostPage()
                .enterTextInInputTitle(title)
                .enterTextInTextAreaBodyContent(testDataForCreatePost.get("body"))
                .checkCheckBoxAndClick(testDataForCreatePost.get("checkbox"))
                .selectTextInDropDownByUI(testDataForCreatePost.get("dropdown"))
                .clickSaveNewPostButton()
                .chekIsRedirectToPostPage()
                .checkTextInAllert("New post successfully created.")
                .checkTextInPostUnique(testDataForCreatePost.get("checkbox"))
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .chekPostWasCreated(title)
        ;
    }
    @After
    public void deletePosts() throws SQLException, ClassNotFoundException {
        homePage.openHomePageWithDataFromDB()
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .deletePostsWithTitleTillPresent(title);

    }


}
