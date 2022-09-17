package postsTest;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class EditPostTest extends BaseTest {
    final static String TITLE = "nikuradze-post-initial";
    final static String BODY = "bla bla";
    final static String ALERT = "New post successfully created.";
    final static String UPDATED_TITLE = "nikuradze-post-changed";
    final static String UPDATED_ALERT = "Post successfully updated.";

    @Before
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
              .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody(BODY)
                .clickOnButtonSaveNewPost()
              .checkIsRedirectToPostPage()
                .checkTextInAlert(ALERT)
                .getHeaderElement().clickOnMyProfileButton()
              .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @Test
    public void editPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
              .checkIsRedirectToMyProfilePage()
                .clickOnPost(TITLE)
              .checkIsRedirectToPostPage()
                .clickOnEditPostButton()
              .checkIsRedirectToEditPostPage()
                .enterUpdatedTextInInputTitle(UPDATED_TITLE)
                .clickOnButtonSaveUpdates()
                .checkTextInAlert(UPDATED_ALERT)
                .getHeaderElement().clickOnMyProfileButton()
              .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(UPDATED_TITLE)
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
              .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(UPDATED_TITLE)
        ;
    }
}
