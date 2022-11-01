package StepDefinitions;

import static libs.DriverHelper.getWebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.MyProfilePage;


public class ProfilePage_StepDefinition {
    MyProfilePage myProfilePage = new MyProfilePage(getWebDriver());

    @Then("^User is redirect to 'Profile' page$")
    public void userIsRedirectToProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
