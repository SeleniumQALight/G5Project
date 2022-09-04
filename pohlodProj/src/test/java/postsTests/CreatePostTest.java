package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "pohlod-post" + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTheInputTitle(TITLE)
                .enterTextIntoTheInputBodyContent("pohlod-body-content")
                //      .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")
                // .selectTextInDropDownRoleByUI("Групове повідомлення")
                .clickOnTheSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;

    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement()
                .clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(TITLE);

    }

}