package postTest;

import categories.SmokeTestFilter;
import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_kolesnyk_" + Util.getDateAndTimeFormatted();
    @Test
    @Category(SmokeTestFilter.class)
    public void createNewPost(){

        homePage
                .openHomePage()
                .getHeaderElements().clickOnButtonCreatePost()
                .checkIsRedirectToPostPage()
                .enterTextIntoInputTitle(TITLE)
                .enterTextIntoInputBody("Text in the body of the article")
                .checkBoxSelectValue("Check")
 //               .selectTextInDropDownRole("Приватне повідомлення")
 //               .selectValueInDropDownRole("One Person")
                .selectDropDown("Приватне повідомлення")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkIsPostMarkedUnique()
                .getHeaderElements().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)

        ;

    }

    @After
    public void deletePosts(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);
    }
}
