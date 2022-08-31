package postTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle("dr-post")
                .enterTextInputBodyContent("dr-Body-Content")
                .selectTextInDropDownByUI(TestData.VALUE_DROPDOWN_CREATE_POST[2])
                //.selectTextInDropDownRole("Приватне повідомлення")
                //.selectValueInDropDownRole("One Person")
                .clickSaveNewPost()
                .checkIsRedirectToPostInfoPage()
                .checkTextInAlert("New post successfully created.")
                .checkTitleInPostInfoPage("dr-post")
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage();
    }


}
