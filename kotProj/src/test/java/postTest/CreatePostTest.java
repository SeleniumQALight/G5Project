package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost (){

    homePage
            .openHomePage()
            .getHeaderElement().clickOnButtonCreatePOst()
            .checkIsRedirectToCreatePostPage()
            .enterTextInInputTitle("title of the post")
          //  .selectTextInDropDownRole("Приватне повідомлення")
            .selectValueInDropDownRole("One Person")
    ;




    }
}

