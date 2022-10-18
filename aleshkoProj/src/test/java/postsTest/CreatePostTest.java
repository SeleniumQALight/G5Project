package postsTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostTest extends BaseTest {
    final String TITLE = "Aleshko_Title_" + Util.getDateAndTimeFormatted();

    Map<String, String> testDataForPostPage = ExcelDriver.getData(configProperties.DATA_FILE(), "postData");
    private String titleFromExcel = testDataForPostPage.get("title");

    public CreatePostTest() throws IOException {
    }

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(TITLE)
                .enterTextIntoBodyInput("")
                .selectValueInDropDownRole("One Person")
                .clickOnSaveNewPostButton()
                .checkRedirectToPostPage();
    }

    @Test
    @Category(SmokeTestFilter.class)
    public void TC2_createNewPostHomeWork() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .enterTextIntoTitleInput(TITLE)
                .enterTextIntoBodyInput("Aleshko_New_BodyText")
                .selectTextInDropDownRoleLikeUI("Групове повідомлення")
                .setCheckBoxWithValue("uncheck")
                .clickOnSaveNewPostButton()
                .checkRedirectToPostPage()
                .checkAlertAboutNewPostCreation("New post successfully created.")
                .checkPostContentAfterNewPostCreation(TITLE, "Aleshko_New_BodyText", "no")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }

    @Test
    public void createPostDBWithExcel() throws SQLException, ClassNotFoundException {
        homePage
                .openHomePageWithDataFromDB()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(titleFromExcel)
                .enterTextIntoBodyInput(testDataForPostPage.get("body"))
                .setCheckBoxWithValue(testDataForPostPage.get("checkbox"))
                .selectTextInDropDownRole(testDataForPostPage.get("dropdown"))
                .clickOnSaveNewPostButton()
                .checkRedirectToPostPage()
                .checkAlertAboutNewPostCreation("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(titleFromExcel);
    }

    @After
    public void deletePosts() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleUntilPresent(titleFromExcel);
    }
}