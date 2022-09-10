package postTests;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String TITLE = "TC1_dr" + Util.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle(TITLE)
                .enterTextInputBodyContent("dr-Body-Content")

                .selectTextInDropDownByUI(TestData.VALUE_DROPDOWN_CREATE_POST[2])
                .putCheckBoxIntoRequiredCondition(TestData.CHECK)
                //.putCheckBoxIntoRequiredCondition(TestData.UNCHECK)
                //.selectTextInDropDownRole("Приватне повідомлення")
                //.selectValueInDropDownRole("One Person")
                .clickSaveNewPost()
                .checkIsRedirectToPostInfoPage()
                .checkTextInAlert("New post successfully created.")
                .checkTextInCheckBox(TestData.CHECK)
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE);
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);
    }

}
