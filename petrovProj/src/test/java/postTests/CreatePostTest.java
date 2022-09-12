package postTests;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "petrov-TC1_"+ Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
             .checkIsRedirectToCreatePage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBodyContent("Text into tag textArea on page create post")
                .actionsWithCheckBox("check")
                .selectOptionInDropDownByUI()
                .selectValueInDropDownRole("One Person")
                .clickOnCreatePostButton()
             .checkIsRedirectToPostPage()
                .checkIsAppliedCheckBox(TestData.PP_CHECK_MESSAGE)
                .checkTestIsAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)

        ;

//                .selectTextInDropDownRole("Приватне повідомлення")
        ;
    }

    @After
    public void DeletePosts(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(TITLE);
    }
}
