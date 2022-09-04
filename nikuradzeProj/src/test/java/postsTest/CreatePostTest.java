package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_nikuradze_" + Util.getDateAndTimeFormatted();
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
              .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("Dummy text in body")
                .selectTextInDropDownByUIRole()
//                .selectTextInDropdownRole("Приватне повідомлення")
//                .selectValueInDropDownRole("One Person")
                .clickOnButtonSaveNewPost()
              .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
              .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;

    }

    @After
    public void deletePosts(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);
    }

}
