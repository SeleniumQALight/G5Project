package postTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Locale;

public class CreatePostTest extends BaseTest {
    final String TITLE ="TC_1_shushkova-post1"+ Util.getDateAndTimeFormatted();

    @Test

    @Category(SmokeTestFilter.class)
    public void TC_1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .selectDropdownElementsUI()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("text")
                .selectValueInDropDownRole("One Person")
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
                .deletePostWithTitleTillPresence(TITLE);

    }
}
