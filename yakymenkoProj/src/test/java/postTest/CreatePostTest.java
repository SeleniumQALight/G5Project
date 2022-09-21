package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_yakymenko-post" + Util.getDateAndTimeFormatted(); // унікальність
    final String BODY = "TC2_yakymenko-bodyContent" + Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()//alt+Enter → HomePage
                .getHeaderElement().clickOnButtonCreatePost() //alt+enter → HomePage
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("test")
                .selectValueInDropDownRole("One Person")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @Test
    public void TC2_createNewPostForHW4_withCheck() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
//                .enterTextInInputBodyContent("Yakymenko-bodyContentForHW4")
                .enterTextInInputBodyContent(BODY)
                .selectValueInDropDownRoleUI("Групове повідомлення")
                .setCheckBoxWithValue("check")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkPostContentAfterCreatedNewPost(TITLE, BODY, "yes")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @Test
    public void TC3_createNewPostForHW4_withUncheck() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBodyContent("Yakymenko-bodyContentForHW4")
                .selectValueInDropDownRoleUI("Групове повідомлення")
                .setCheckBoxWithValue("uncheck")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkPostContentAfterCreatedNewPost(TITLE, "Yakymenko-bodyContentForHW4", "no")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @After
    public void deletePosts() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresents(TITLE);
    }
}
