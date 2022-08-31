package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    @Test
    public void createNewPost() {
        //TODO body input, select from dropdown
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle("boiaryntseva-post")
                // .selectTextInDropDownRole("Приватне повідомлення");
                // .selectValueInDropDownRole("One Person");
                .enterTextInputBody("Text entered into body")
                .selectValueInDropDown()
                .savePost()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage();

    }
}
