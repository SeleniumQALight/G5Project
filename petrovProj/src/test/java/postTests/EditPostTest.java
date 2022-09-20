package postTests;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String TITLE = "petrov-TC1_"+ Util.getDateAndTimeFormatted();
    String contentText = "Text into tag textArea on page create post";
    String checkBoxAction = "check";
    String role = "One Person";
    private String TitleAfterEdit = "PETROV-Edit_TITLE_afterCreated_v1";





    @Before
    public void createPostForEditTest(){
        homePage    .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .fillInputCreatePostForm(TITLE, contentText, checkBoxAction, role)
                .clickOnCreatePostButton()
                .checkIsRedirectToPostPage()
                .checkIsAppliedCheckBox(TestData.PP_CHECK_MESSAGE)
                .checkTestIsAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }

    @Test
    public void editPost(){
        homePage.getHeaderElement()
                .clickOnMyProfileButton()
                .clickOnSelectPostByTitle(TITLE)
                .clickOnEditButton()
              //.checkIsRedirectToEditPostPage()
                .editEnterTextInInputTitle(TitleAfterEdit)
                .clickOnSaveUpdateButton()
                .checkIsMessageSuccess()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsNewTitle(TitleAfterEdit);

    }

    @After
    public void deletePostAfterEditTest(){
            homePage
                    .openHomePage()
                    .getHeaderElement().clickOnMyProfileButton()
                    .checkIsRedirectToMyProfilePage()
                    .deletePostWithTitleTillPresent(TitleAfterEdit);
    }
}
