package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    @Test
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
          .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("kniazieva-post")
                //.selectTextInDropDownRole("Приватне повідомлення")
                //.selectValueInDropDownRole("One Person")
                .selectTextInDropDownUI()
                .enterTextInInputBodyContent("test text for body content")
                .clickTheSaveButton()
          .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
          .checkIsRedirectToMyProfilePage()

        ;
    }
}
