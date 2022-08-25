package postsTests;

import org.junit.Test;

import baseTest.BaseTest;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
              .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("radulenko-post")
//                .selectTextInDropDownRole("Приватне повідомлення")
                .selectValueInDropDownRole("One Person")

        ;
    }
}
