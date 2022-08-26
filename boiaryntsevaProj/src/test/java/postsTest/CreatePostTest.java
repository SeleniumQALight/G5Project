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
                //    .selectTextInDropDownRole("Приватне повідомлення");
                .selectValueInDropDownRole("One Person");

    }
}
