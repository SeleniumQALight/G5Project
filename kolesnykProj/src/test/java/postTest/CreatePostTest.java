package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    @Test
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnButtonCreatePost()
                .checkIsRedirectToPostPage()
                .enterTextIntoInputTitle("Ross - post")
 //               .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")
        ;



    }
}
