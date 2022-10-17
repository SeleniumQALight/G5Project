package postsTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {
    final String TITLE = "pohlod-post" + Util.getDateAndTimeFormatted();
    final String STATE = "check";

    @Test
    @Category(SmokeTestFilter.class)
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTheInputTitle(TITLE)
                .enterTextIntoTheInputBodyContent("pohlod-body-content")
                .checkboxIsPostUniqueState(STATE)
                //      .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")
                // .selectTextInDropDownRoleByUI("Групове повідомлення")
                .clickOnTheSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkIsPostUnique(STATE)
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
