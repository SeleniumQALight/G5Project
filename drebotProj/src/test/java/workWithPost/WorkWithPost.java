package workWithPost;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MyProfilePage;

public class WorkWithPost extends BaseTest {

    final String TITLE = "TC1_dr" + Util.getDateAndTimeFormatted();
    final String TITLE_NEW = TITLE + "_NEW";

    @Before
    public void preconditionToEditPost() {
        homePage.openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle(TITLE)
                .enterTextInputBodyContent("create body of new post")
                .clickSaveNewPost()
                .checkIsRedirectToPostInfoPage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
                .checkPostNotExist(TITLE_NEW);
    }

    @Test
    public void editTitlePost() {

        homePage.getHeaderElement().clickOnButtonMyProfile()
                .editPost(TITLE, TITLE_NEW)
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE_NEW)
                .checkPostNotExist(TITLE);
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE_NEW)
                .deletePostsWithTitleTillPresent(TITLE);
    }
}
