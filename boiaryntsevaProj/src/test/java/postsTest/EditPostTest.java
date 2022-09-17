package postsTest;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String TITLE = "boiaryntseva" + Math.random();
    final String TITLE_EDITED = TITLE + "Edited";

    @Before
    public void createPost(){
        homePage
                .openHomePage()
                .getHeaderElement()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle(TITLE)
                .enterTextInputBody("Text entered into body")
                .selectValueForCheckBox("check")
                .selectValueInDropDown()
                .savePost()
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }
    @Test
    public void editPostTitle() {
        homePage.getHeaderElement()
                .clickOnMyProfileButton()
                .clickOnPostWithTitle(TITLE)
                .clickOnEditButton()
                .checkRedirectToEditPage()
                .enterTextIntoTitleWithoutClearingInput("Edited") // or enterTextInputTitle if whole Title should be edited
                .saveEditedPost()
                .checkTextInAlert("Post successfully updated.")
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkPostWasCreated(TITLE_EDITED);
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(TITLE)
                .deletePostWithTitleTillPresent(TITLE_EDITED);
    }

}


