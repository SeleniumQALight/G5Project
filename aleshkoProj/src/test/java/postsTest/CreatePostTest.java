package postsTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {
    final String TITLE = "Aleshko_Title_" + Util.getDateAndTimeFormatted();

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

    @After
    public void deletePosts() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleUntilPresent(TITLE);
    }
}