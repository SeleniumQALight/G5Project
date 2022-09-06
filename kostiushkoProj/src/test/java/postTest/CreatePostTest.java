package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.CreatePostPage;

import java.util.concurrent.TimeUnit;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_Kostiushko" + Util.getDateAndTimeFormatted();


    @Test
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
                .clikSaveNewPostButton()
                .chekIsRedirectToPostPage()
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
