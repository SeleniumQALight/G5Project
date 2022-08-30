package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePotsPage()
                .enterTextInInputTitle("knutarova-post")
//              .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")
                .enterTextInInputBody("It's my grate post")
                .selectTextInDropDownByUI("Групове повідомлення")
                .clickOnButtonSaveNewPost()




        ;

    }
}
