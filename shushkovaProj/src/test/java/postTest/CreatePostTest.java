package postTest;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String Title="radulenko-post1";
    @Test
    public void TC_1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("shushkova-post")
                .selectTextInDropDownRole("Приватне повідомлення")
//                .checkPostWasCreated(Title)
                ;

    }
    @After
    public void deletePosts(){
        homePage
                .openHomePage();
 //               .
    }
}
