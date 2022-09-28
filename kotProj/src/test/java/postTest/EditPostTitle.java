package postTest;

import baseTest.BaseTest;
import com.beust.ah.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTitle extends BaseTest {

    private final String TITLE = "Kot_Post";
    private final String POST_BODY = "Text_In_Post";
    private final String ALERT = "New post successfully created.";
    private final String NEW_TITLE = "Kot_Post_Edited";


    @Before

    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePOst()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectValueInDropDownUI()
                .clickOnSavePostButton()
                .checkIsRedirectedToPostPage()
                .checkTextInAlert(ALERT)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }

    @Test
    public void editPostTitle() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .clickOnCreatedPost(TITLE)
                .clickOnEditPostButton()
                .checkIsRedirectedToEditPostPage()
                .clickOnTitleInput(NEW_TITLE)
                .clickOnSaveUpdatesButton()
                .updateSuccess("Post successfully updated.")
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
                .deletePostsWithTitleTillPresent(NEW_TITLE);

    }
}