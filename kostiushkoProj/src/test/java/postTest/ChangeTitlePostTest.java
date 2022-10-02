package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangeTitlePostTest extends BaseTest {

    final String TITLE = "TC1_Kostiushko" + Util.getDateAndTimeFormatted();

    @Before
    public void createNewPost() {


        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .chekIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .selectTextInDropDownByUI("Групове повідомлення")
                .enterTextInTextAreaBodyContent("Text for body")
                .clickSaveNewPostButton()
                .chekIsRedirectToPostPage()
                .checkTextInAllert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .chekPostWasCreated(TITLE);
    }

    @Test
    public void changePostTitle() {

        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .findOldPostWithTitle(TITLE).clickOnButtonTitlePost(TITLE)
                .clickOnEditButton()
                .checkIsRedirectToOldPostPage()
                .enterNewTextInInputTitle("NEW "+TITLE)
                .clikSaveUpdatesPostButton()
                .checkSuccessfulMessage()
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .chekPostWasCreated("NEW "+TITLE);

        ;
    }

    @After
    public void deletePosts() {
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE)
                .deletePostsWithTitleTillPresent("NEW "+TITLE);
    }
}
