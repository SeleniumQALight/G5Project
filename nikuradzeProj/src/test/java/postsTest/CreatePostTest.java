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
                .enterTextInInputBody("Dummy text in body")
                .selectTextInDropdownRole("Приватне повідомлення")
                .clickOnButtonSaveNewPost();
//                .selectValueInDropDownRole("One Person")
        ;

    }
}
