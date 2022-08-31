package PostTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostsTest extends BaseTest {
    @Test
    public void createNewPost(){
homePage.openHomePage()
        .getHeaderElement().clickOnButtonCreatePost()
        . checkIsRedirectToCreatePostPage()
        .enterTextInputTitle("nisavic-post")
        .enterTextInputBody("test")
       // .selectTextInFropdown("Приватне повідомлення")
        .selectValueInDropdownRole("One Person")
        .clickOnSavePostButton ()
        .checkIsRedirectToPostPage()
        .checkTextInAlert("New post successfully created.")
        .getHeaderElement().clickOnMyProfileButton()
        .checkIsRedirectTpMyProfilePage()


        ;
    }
}
