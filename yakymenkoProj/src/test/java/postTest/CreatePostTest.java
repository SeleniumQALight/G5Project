package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_yakymenko-post" + Util.getDateAndTimeFormatted(); // унікальність

    @Test
    public void TC1_createNewPost() {
        homePage // метод написання ланцюгів
                .openHomePage()//alt+Enter → HomePage
                .getHeaderElement().clickOnButtonCreatePost() //alt+enter → HomePage
                .checkIsRedirectToCreatePostPage()
//                .enterTextInInputTitle("yakymenko-post")
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("test")
//                .selectTextInDropDownRole("Приватне повідомлення") //alt+enter → CreatePostPage
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
    public void deletePosts() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresents(TITLE);
    }

    @Test
    public void createNewPostForHW() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("yakymenko-postForHW")
                .enterTextInInputBodyContent("Yakymenko-bodyContentForHW")
//                .selectValueInDropDownRoleUI()
// ↓↑
                .selectValueInDropDownRoleUI("Групове повідомлення")
                .clickOnSaveNewPostButton()

        ;
    }
}
