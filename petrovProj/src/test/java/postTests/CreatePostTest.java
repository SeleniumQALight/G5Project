package postTests;

import baseTest.BaseTestComplexApp;
import categories.SmokeTestFilter;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTestComplexApp {
    final String TITLE = "petrov-TC1_"+ Util.getDateAndTimeFormatted();
    String contentText = "Text into tag textArea on page create post";
    String checkBoxAction = "check";
    String role = "One Person";

    @Test
    @Category(SmokeTestFilter.class)
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
             .checkIsRedirectToCreatePostPage()
            .fillInputCreatePostForm(TITLE, contentText, checkBoxAction, role)
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
