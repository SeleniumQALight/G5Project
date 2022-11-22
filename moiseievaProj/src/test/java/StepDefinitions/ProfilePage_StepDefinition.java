package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.MyProfilePage;

import static libs.DriverHelper.getWebDriver;

public class ProfilePage_StepDefinition {
    MyProfilePage myProfilePage = new MyProfilePage(getWebDriver());

    @Then("^User is redirect 'Profile' page$")
    public void userIsRedirectProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
