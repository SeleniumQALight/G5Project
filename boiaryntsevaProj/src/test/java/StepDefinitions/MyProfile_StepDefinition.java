package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libs.TestData;
import pages.MyProfilePage;

import static libs.DriverHelper.getWebDriver;

public class MyProfile_StepDefinition {
    MyProfilePage myProfilePage = new MyProfilePage(getWebDriver());

    @Then("^User is redirected to 'Profile' page$")
    public void userIsRedirectedToProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumOfPosts);
    }
}
