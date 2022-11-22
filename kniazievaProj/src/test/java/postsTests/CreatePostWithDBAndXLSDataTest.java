package postsTests;

import baseTest.BaseTest;
import libs.DB_UtilHW;
import libs.ExcelDriver;
import org.junit.After;
import org.junit.Test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithDBAndXLSDataTest extends BaseTest {

    String login = "newqaauto";

    DB_UtilHW db_utilHW = new DB_UtilHW();
    String password = db_utilHW.getPasswordForLogin(login);

    public CreatePostWithDBAndXLSDataTest() throws SQLException, ClassNotFoundException, IOException {}

    Map<String, String> dataForPost = ExcelDriver.getData(configProperties.DATA_FILE(), "dataForPost");

    String title = dataForPost.get("title");

    @Test
    public void createPostWithDataBaseAndXLS(){

        loginPage.openLoginPage()
                 .enterUserNameIntoLoginInput(login)
                 .enterPasswordIntoInputPassword(password)
                 .clickOnButtonLogIn()
                 .getHeaderElement().clickOnButtonCreatePost()
             .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(title)
                .selectValueInDropDownRole(dataForPost.get("dropDownValue"))
                .enterTextInInputBodyContent(dataForPost.get("body"))
                .stateOfCheckBox(dataForPost.get("checkbox"))
                .clickTheSaveButton()
             .checkIsRedirectToPostPage()
                .checkTextInAlert("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(title)
        ;
    }

    @After

    public void deletePosts(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(title);
    }
}
