package postTest;

import baseTest.BaseTest;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CreatePostTest extends BaseTest {


    @Test
    public void createNewPost (){

    homePage
            .openHomePage()
            .getHeaderElement().clickOnButtonCreatePost()
            .chekIsRedirectToCreatePostPage()
           .enterTextInInputTitle("Kostiushko")
//            .selecttextInDropDownRole("Приватне повідомлення")
            .selectValueInDropDownRole("One Person")

            ;
    }


}
