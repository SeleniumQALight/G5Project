package postsTest;

import BaseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_slobodyanuyk-post1" + Util.getDateAndTimeFormatted();
    @Test
    public void TC1_createNewPost () {

        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("Slobodyanuyk-Body")
                .selectTextInDropDownUi("Групове поввідомлення")
                .clickOnButtonSaveNewPost()
                //.selectTextInDropDownRole("Приватне повідомлення")
                //.selectValueInDropDownRole("One Person")
        .checkIsRedirectToPostPage()
                .checkTestInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }
    @After
    public void deletedPosts() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);


    }
}
