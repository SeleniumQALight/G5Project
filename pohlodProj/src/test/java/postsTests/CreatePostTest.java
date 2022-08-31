package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTheInputTitle("pohlod-post")
                .enterTextIntoTheInputBodyContent("pohlod-body-content")
                //      .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")
                // .selectTextInDropDownRoleByUI("Групове повідомлення")
                .clickOnTheSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
        ;

    }

}
