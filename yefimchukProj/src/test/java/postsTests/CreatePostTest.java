package postsTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_yefimchuk-post1" + Util.getDateAndTimeFormatted();

    @Test
    @Category(SmokeTestFilter.class)
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("test")

                //   .selectTextInDropdownRole("Приватне повідомлення")
                .selectValueInDropdownRole("One Person")
                .clickOnSavePostButton()
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
