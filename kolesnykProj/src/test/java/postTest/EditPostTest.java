package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.apache.poi.ss.formula.functions.T;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String TITLE = "TC1_kolesnyk_" + Util.getDateAndTimeFormatted();

    @Before
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElements().clickOnButtonCreatePost()
                .checkIsRedirectToPostPage()
                .enterTextIntoInputTitle(TITLE)
                .enterTextIntoInputBody("Text in the body of the article")
                .checkBoxSelectValue("Check")
                .selectDropDown("Приватне повідомлення")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkIsPostMarkedUnique()
                .getHeaderElements().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }


    @Test
    public void editPost(){
       myProfilePage
               .clickOnPost(TITLE)
               .clickOnEditButton()
               .editPostTitle("_EDIT")
               .clickOnButtonSaveUpdates()
               .checkTextInAlert("Post successfully updated.")
               .getHeaderElements().clickOnButtonMyProfile()
               .checkIsRedirectToMyProfilePage()
               .checkPostWasCreated(TITLE + "_EDIT");
    }

    @After
    public void deletePosts(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE)
                .deletePostsWithTitleTillPresent(TITLE + "_EDIT");
    }

}
