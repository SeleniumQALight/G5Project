package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleInput("aleshko-post")
//                .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")

        ;

    }
}