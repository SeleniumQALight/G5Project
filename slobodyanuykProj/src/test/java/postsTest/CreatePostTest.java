package postsTest;

import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CreatePostPage;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost () {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle("Slobodyanuyk-post")
                .enterTextInInputBody("Slobodyanuyk-Body")
                .selectTextInDropDownUi("Групове поввідомлення")
                .clickOnButtonSaveNewPost()
                //.selectTextInDropDownRole("Приватне повідомлення")
                //.selectValueInDropDownRole("One Person");
        ;
    }
}
