package postTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {
    String TITLE;
    String BODY;

    @Before
    public void setUpTestName() {
        TITLE = testName1 + "-yakymenko-" + Util.getDateAndTimeFormatted();
        BODY = testName1 + "-yakymenko-" + Util.getDateAndTimeFormatted();
    }

    @Test
    @Category(SmokeTestFilter.class)
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
