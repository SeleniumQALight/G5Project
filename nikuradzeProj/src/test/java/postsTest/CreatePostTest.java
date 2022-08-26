package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
              .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("nikuradze-post")
//                .selectTextInDropdownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")
        ;

    }
}
