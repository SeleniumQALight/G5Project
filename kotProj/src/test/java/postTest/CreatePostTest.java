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
            .enterTextInInputBody("text")
          //  .selectTextInDropDownRole("Приватне повідомлення")
            .selectValueInDropDownRole("One Person")
            .clickOnSavePostButton()
       .checkIsRedirectedToPostPage()
            .checkTextInAlert("New post successfully created.")
            .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
    ;




    }
}

