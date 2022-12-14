package postTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePostTest extends BaseTest {

    final String TITLE = "TC1_Kot " + Util.getDateAndTimeFormatted();
    @Test
    @Category(SmokeTestFilter.class)
    public void TC1_createNewPost (){

    homePage
            .openHomePage()
            .getHeaderElement().clickOnButtonCreatePOst()
        .checkIsRedirectToCreatePostPage()
            .enterTextInInputTitle(TITLE)
            .enterTextInInputBody("text")
          //  .selectTextInDropDownRole("Приватне повідомлення")
            //.selectValueInDropDownRole("One Person")
            .selectValueInDropDownUI()
            .clickOnSavePostButton()
       .checkIsRedirectedToPostPage()
            .checkTextInAlert("New post successfully created.")
            .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
            .checkPostWasCreated(TITLE)
    ;

    }

    @Test
    public void TC2_CreateUniquePost(){
        homePage.openHomePage()
        .getHeaderElement().clickOnButtonCreatePOst()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(TITLE)
                .enterTextInInputBody("Unique")
                .checkBoxIsClicked("Checked")
                .selectValueInDropDownUI()
                .clickOnSavePostButton()
                .checkIsRedirectedToPostPage()
                .checkTextInAlert("New post successfully created.")
                .checkPostIsUnique()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
                ;




    }
@After
public void deletePost(){
   homePage
           .openHomePage()
           .getHeaderElement().clickOnMyProfileButton()
           .checkIsRedirectToMyProfilePage()
           .deletePostsWithTitleTillPresent(TITLE)  ;

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

