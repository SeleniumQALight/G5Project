package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        homePage // метод написання ланцюгів
                .openHomePage()//alt+Enter → HomePage
                .getHeaderElement().clickOnButtonCreatePost() //alt+enter → HomePage
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("yakymenko-post")
//                .selectTextInDropDownRole("Приватне повідомлення") //alt+enter → CreatePostPage
                .selectValueInDropDownRole("One Person")
        ;


    }
}
