package postsTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithDBAndExcelTest extends BaseTest {
    Map<String, String> testDataForPostPage = ExcelDriver.getData(configProperties.DATA_FILE(), "postData");

    public CreatePostWithDBAndExcelTest() throws IOException {
    }

    @Test
    public void createPostDBWithExcel() throws SQLException, ClassNotFoundException {

        homePage
                .openHomePageWithDataFromDB()
                .getHeaderElement().clickOnButtonCreatePost()
              .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(testDataForPostPage.get("title"))
                .enterTextInInputBody(testDataForPostPage.get("body"))
                .setCheckboxValue(testDataForPostPage.get("checkbox"))
                .selectTextInDropdownRole(testDataForPostPage.get("dropdown"))
                .clickOnButtonSaveNewPost()
              .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkPostUniqueness()
                .getHeaderElement().clickOnMyProfileButton()
              .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(testDataForPostPage.get("title"))
        ;
    }
    @After
    public void deletePosts(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(testDataForPostPage.get("title"));
    }
}
