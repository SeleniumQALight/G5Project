package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput("aleshko-post")
                .enterTextIntoBodyInput("")
                .selectValueInDropDownRole("One Person")
                .clickOnSaveNewPostButton()
                .checkRedirectToPostPage();
    }


    @Test
    public void createNewPostHomeWork() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .enterTextIntoTitleInput("Aleshko_New_Title")
                .enterTextIntoBodyInput("Aleshko_New_BodyText")
                .selectTextInDropDownRoleLikeUI("Групове повідомлення")
                .clickOnSaveNewPostButton()
                .checkRedirectToPostPage()
                .checkAlertAboutNewPostCreation("New post successfully created.")
                .checkTitleAndBodyTextsAfterNewPostCreation("Aleshko_New_Title", "Aleshko_New_BodyText")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
        ;
    }
}