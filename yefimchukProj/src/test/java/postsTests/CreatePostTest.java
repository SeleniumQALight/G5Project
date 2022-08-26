package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("yefimchuk-post")
             //   .selectTextInDropdownRole("Приватне повідомлення")
                .selectValueInDropdownRole("One Person")
        ;
    }
}
