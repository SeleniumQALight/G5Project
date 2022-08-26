package postTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePage()
                .enterTextInInputTitle("petrov-post_v1")
                .enterTextInInputBodyContent("Text into tag textArea on page create post")
                .selectOptionInDropDownByUI("Групове повідомлення")
 //               .selectValueInDropDownRole("One Person")
                .clickOnCreatePostButton();

//                .selectTextInDropDownRole("Приватне повідомлення")
        ;
    }
}
