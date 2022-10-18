package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangeTitle extends BaseTest {

    final String TITLE = "TCHW_kniazieva" + Util.getDateAndTimeFormatted();

    @Before
    public void createAndCheckPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .enterTextInInputTitle(TITLE)
                .selectTextInDropDownUI()
                .enterTextInInputBodyContent("test text for body content homework")
                .stateOfCheckBox("check")
                .clickTheSaveButton()
                .getHeaderElement().clickOnMyProfileButton()
                .checkPostWasCreated(TITLE)
        ;
    }


    @Test
    public void changePostTitle(){
        myProfilePage
                .clickOnPostForChangeTitle(TITLE)
                .clickOnEditButton()
                .enterTextInInputTitle(TITLE+" CHANGE")
                .clickTheUpdateButton()
                .checkTextInAlert("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkPostWasCreated(TITLE + " CHANGE")
        ;

    }

    @After
    public void deleteRenamedPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .deletePostsWithTitleTillPresent(TITLE + " CHANGE");
    }
}
