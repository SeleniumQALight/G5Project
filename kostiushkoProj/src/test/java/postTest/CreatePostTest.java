package postTest;

import baseTest.BaseTest;
import catagories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_Kostiushko" + Util.getDateAndTimeFormatted();
    static String checkBox = "check";


    @Test
    @Category(SmokeTestFilter.class)
    public void createNewPost() {

        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .chekIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
//            .selecttextInDropDownRole("Приватне повідомлення")
//            .selectValueInDropDownRole("One Person")
                .selectTextInDropDownByUI("Групове повідомлення")
                .enterTextInTextAreaBodyContent("Text for body")
                .checkCheckBoxAndClick(checkBox)
                .clickSaveNewPostButton()
                .chekIsRedirectToPostPage()
                .checkTextInPostUnique(checkBox)
                .checkTextInAllert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .chekPostWasCreated(TITLE)
        ;
    }

    @After
    public void deletePosts(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);

    }

}
