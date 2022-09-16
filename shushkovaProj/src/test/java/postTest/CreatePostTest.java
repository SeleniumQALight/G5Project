package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE ="TC_1_shushkova-post1"+ Util.getDateAndTimeFormatted();
    @Test
    public void TC_1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .selectTextInDropDownRole("Загальнодоступне")
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("text")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
                ;
    }
    @After
    public void deletePosts(){
     //   homePage
          //      .openHomePage()
           //     .getHeaderElement().clickOnMyProfileButton()
           //     .checkIsRedirectToMyProfilePage()
           //     .deletePostWithTitleTillPresence(TITLE)
       // ;
 //               .
    }
}
