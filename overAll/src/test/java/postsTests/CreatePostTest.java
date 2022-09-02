package postsTests;

import org.junit.After;
import org.junit.Test;

import baseTest.BaseTest;
import libs.Util;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_radulenko" + Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
             .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("test")
//                .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")
                .clickOnSavePostButton()
             .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @After
    public void deletePosts(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);
    }

}
