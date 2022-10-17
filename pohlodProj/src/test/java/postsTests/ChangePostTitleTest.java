package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangePostTitleTest extends BaseTest {
    final String TITLE = "pohlod-post" + Util.getDateAndTimeFormatted();
    final String NEWTITLE = "NEW TITLE " + Util.getDateAndTimeFormatted();


    @Before
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTheInputTitle(TITLE)
                .enterTextIntoTheInputBodyContent("pohlod-body-content")
                .selectValueInDropDownRole("One Person")
                .clickOnTheSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @Test
    public void editPostTitle (){
        homePage
                .openHomePage()
                .getHeaderElement()
                .clickOnButtonProfile()
                .checkPostWasCreated(TITLE)
                .clickOnThePost(TITLE)
                .checkIsRedirectToPostPage()
                .clickOnTheEditButton()
                .editTitle(NEWTITLE)
                .clickOnTheSaveUpdatesButton()
                .checkTextInAlert("Post successfully updated.")
                ;

    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement()
                .clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(NEWTITLE)
                .deletePostWithTitleTillPresent(TITLE);

    }

}
