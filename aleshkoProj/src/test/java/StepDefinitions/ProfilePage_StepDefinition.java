package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.MyProfilePage;

public class ProfilePage_StepDefinition {
    MyProfilePage profilePage = new MyProfilePage(DriverHelper.getWebDriver());

    @Then("^User is redirect to 'Profile' page$")
    public void userIsRedirectToProfilePage() {
        profilePage.checkIsRedirectToMyProfilePage();
    }


    @And("^User sees (\\d+) posts in Posts list on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
