package postTests;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

public class CreatePostWithExcelAndDBTest extends BaseTest {
    Map<String, String> dataFromExcel = ExcelDriver.getData(CommonActionsWithElements.configProperties.DATA_FILE(), "createPost");
    final String TITLE = dataFromExcel.get("title") + Util.getDateAndTimeFormatted();

    public CreatePostWithExcelAndDBTest() throws IOException {
    }

    @Test
    public void createPostWithExcelAndDB() {

        loginPage.openLoginPage()
                .loginWithValidCredential()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle(TITLE)
                .enterTextInputBodyContent(dataFromExcel.get("body"))
                .putCheckBoxIntoRequiredCondition(dataFromExcel.get("checkBox"))
                .selectTextInDropDownRole(dataFromExcel.get("dropDown"))
                .clickSaveNewPost()
                .checkIsRedirectToPostInfoPage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(TITLE)
        ;
    }

    @After
    public void postCondition() {
        homePage.openHomePage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE);
    }
}
