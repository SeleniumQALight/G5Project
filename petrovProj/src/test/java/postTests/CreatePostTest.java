package postTests;

import baseTest.BaseTest;
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
                .selectOptionInDropDownByUI()
 //               .selectValueInDropDownRole("One Person")
                .clickOnCreatePostButton()
             .checkIsRedirectToPostPage()
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
