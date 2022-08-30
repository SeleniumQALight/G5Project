package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        homePage // метод написання ланцюгів
                .openHomePage()//alt+Enter → HomePage
                .getHeaderElement().clickOnButtonCreatePost() //alt+enter → HomePage
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("yakymenko-post")
                .enterTextInInputBody("test")
//                .selectTextInDropDownRole("Приватне повідомлення") //alt+enter → CreatePostPage
                .selectValueInDropDownRole("One Person")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
        ;
    }

    @Test
    public void createNewPostForHW() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("yakymenko-postForHW")
                .enterTextInInputBodyContent("Yakymenko-bodyContentForHW")
                .selectValueInDropDownRoleUI()
                .clickOnSaveNewPostButton()

        ;
    }
}
