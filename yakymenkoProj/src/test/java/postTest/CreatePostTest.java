package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() { //alt+Enter → HomePage
        homePage // метод написання ланцюгів
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost() //alt+enter → HomePage
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("yakymenko-post")
//                .selectTextInDropDownRole("Приватне повідомлення") //alt+enter → CreatePostPage
                .selectValueInDropDownRole("One Person")
        ;


    }
}
