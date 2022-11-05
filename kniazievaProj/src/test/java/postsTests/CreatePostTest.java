package postsTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {

    final String TITLE = "TC1_kniazieva" + Util.getDateAndTimeFormatted();

    @Test
    @Category(SmokeTestFilter.class)
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
          .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                //.selectTextInDropDownRole("Приватне повідомлення")
                //.selectValueInDropDownRole("One Person")
                .selectTextInDropDownUI()
                .enterTextInInputBodyContent("test text for body content")
                .stateOfCheckBox("check")
                .clickTheSaveButton()
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
