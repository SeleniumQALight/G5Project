package editPostTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String TITLE ="TC_2_shushkova-post1"+ Util.getDateAndTimeFormatted();
    final String NEWTITLE="TC_2_shushkova-postEdit"+Util.getDateAndTimeFormatted();
    @Before
    public void TC_2_createNewPostAndEdit(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .selectDropdownElementsUI()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("text")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }
    @Test
    public void TC_2_findPostAndEdit(){
        myProfilePage
                .clickOnPostWithTitle(TITLE)
      //          .checkToRedirectToEditPage()
                .clickOnEditButton()
                .enterTextInInputTitle(NEWTITLE)
                .clickOnButtonSaveUpdates()
                .checkThatAlertOfSuccessEditIsDisplayed();


    }
    @After
    public void deletePosts(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresence(NEWTITLE);

    }
}
