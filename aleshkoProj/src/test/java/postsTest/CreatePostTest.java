package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput("aleshko-post")
//                .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person");
    }


    @Test
    public void createNewPostHomeWork() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .enterTextIntoTitleInput("New_Title")
                .enterTextIntoBodyInput("New_BodyText")
                .selectTextInDropDownRoleLikeUI("Групове повідомлення")
                .clickOnSaveNewPostButton()
                .checkRedirectToPostPageAfterNewPostCreation()
                .checkTitleAndBodyTextsAfterNewPostCreation("New_Title", "New_BodyText");
    }
}