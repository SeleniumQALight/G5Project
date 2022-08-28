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
                .enterTextIntoInputBody("Text in the body of the article")
 //               .selectTextInDropDownRole("Приватне повідомлення")
//                .selectValueInDropDownRole("One Person")
                .selectDropDown("Приватне повідомлення")
                .clickOnButtonSaveNewPost();
        ;



    }
}
