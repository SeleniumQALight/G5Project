package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MyProfilePage;

public class ReplacementTitleOfPost extends BaseTest {
    final String TITLE = "TC2_knutarova_" + Util.getDateAndTimeFormatted();
    final String TITLE2 = "TC_knutarova_newTitle_" + Util.getDateAndTimeFormatted();
    @Before
    public void CreatePost(){
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput("qaauto")
                .enterPasswordIntoInputPassword("123456qwerty")
                .clickOnButtonLogIn()
                .clickOnButtonCreatePost()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("It's my second grate post")
                .clickOnButtonSaveNewPost()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
                .clickOnButtonSignOut()
        ;
    }
    @Test
    public void TC2_replacementTitleOfPost(){
        loginPage
                .openLoginPage()
                .enterUsernameIntoLoginInput("qaauto")
                .enterPasswordIntoInputPassword("123456qwerty")
                .clickOnButtonLogIn()
                .clickOnMyProfileButton()
                .clickOnPost(TITLE)
                .clickOnButtonEdit()
                .editTitle(TITLE2)
                .clickOnButtonSaveUpdates()
                .checkMessagePostUpdated()
                .clickOnMyProfileButton()
                .checkTitleWasEdited(TITLE2)
        ;
    }
    @After
    public void deletePosts(){
        loginPage
                .openLoginPage()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE)
                .deletePostsWithTitleTillPresent(TITLE2)
        ;
    }
}
