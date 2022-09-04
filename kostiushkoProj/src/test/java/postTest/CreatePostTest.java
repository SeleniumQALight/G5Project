package postTest;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.CreatePostPage;

import java.util.concurrent.TimeUnit;

public class CreatePostTest extends BaseTest {


    @Test
    public void createNewPost() {

        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .chekIsRedirectToCreatePostPage()
                .enterTextInInputTitle("Kostiushko")
//            .selecttextInDropDownRole("Приватне повідомлення")
//            .selectValueInDropDownRole("One Person")
                .selectTextInDropDownByUI("Групове повідомлення")
                .enterTextInTextAreaBodyContent("Text for body")
                .clikSaveNewPostButton()
                .chekIsRedirectToPostPage()
                .checkTextInAllert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .chekIsRedirectTomyProfilePage()

        ;
    }


}
