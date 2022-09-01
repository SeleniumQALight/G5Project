package PostTest;

import Libs.Util;
import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreatePostsTest extends BaseTest {
    final String TITLE = "nisavic_post1" + Util.getDateAndTimeFormatted();
    @Test
    public void createNewPost(){
homePage.openHomePage()
        .getHeaderElement().clickOnButtonCreatePost()
        . checkIsRedirectToCreatePostPage()
        .enterTextInputTitle(TITLE)
        .enterTextInputBody("test")
       // .selectTextInFropdown("Приватне повідомлення")
        .selectValueInDropdownRole("One Person")
        .clickOnSavePostButton ()
        .checkIsRedirectToPostPage()
        .checkTextInAlert("New post successfully created.")
        .getHeaderElement().clickOnMyProfileButton()
        .checkIsRedirectTpMyProfilePage()
        .checkPostWasCreated(TITLE)


        ;
    }

    @After
    public void deletePosts () {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectTpMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);
    }
    }

