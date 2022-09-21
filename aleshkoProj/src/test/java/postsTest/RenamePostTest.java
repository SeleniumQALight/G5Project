package postsTest;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RenamePostTest extends BaseTest {
    private final String TITLE = "Aleshko-Title";
    private final String BODYTEXT = "BodyText";
    private final String ALERT = "New post successfully created.";
    private final String NEW_TITLE = "Aleshko-Title-NEW";


    @Before
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput(TITLE)
                .enterTextIntoBodyInput(BODYTEXT)
                .clickOnSaveNewPostButton()
                .checkRedirectToPostPage()
                .checkAlertAboutNewPostCreation(ALERT)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }

    @Test
    public void editPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .findAndClickOnPostByTitle(TITLE)
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage()
                .inputNewTextIntoTitleField(NEW_TITLE)
                .clickOnButtonSaveChanges()
                .checkTextInAlert("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(NEW_TITLE);
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleUntilPresent(TITLE)
                .deletePostWithTitleUntilPresent(NEW_TITLE);
    }
}
