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
            //.selectValueInDropDownRole("One Person")
            .selectValueInDropDownUI()
            .clickOnSavePostButton()
       .checkIsRedirectedToPostPage()
            .checkTextInAlert("New post successfully created.")
            .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
    ;

    }


    @Test
    public void createGroupPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePOst()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("Group post")
                .enterTextInInputBody("The 1-st of September")
                .selectValueInDropDownUI()
                .clickOnSavePostButton()
                .checkIsRedirectedToPostPage();




    }
}

