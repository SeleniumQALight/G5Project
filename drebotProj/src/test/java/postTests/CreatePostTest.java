package postTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle("dr-post")
                //.selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person");
    }


}
