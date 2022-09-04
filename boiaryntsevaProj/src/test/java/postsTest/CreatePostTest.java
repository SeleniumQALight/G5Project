package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "boiaryntseva-post1" + Util.getDateAndTimeFormatted();
// TODO   enum checkBoxValue {check, uncheck}
    @Test
    public void createNewPost() {
        //TODO body input, select from dropdown
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle(TITLE)
                // .selectTextInDropDownRole("Приватне повідомлення");
                // .selectValueInDropDownRole("One Person");
                .enterTextInputBody("Text entered into body")
                .selectValueForCheckBox("check")
                .selectValueInDropDown()
                .savePost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .validateCheckBoxStateOnPost("check")
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);

    }


    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(TITLE);
    }
}
