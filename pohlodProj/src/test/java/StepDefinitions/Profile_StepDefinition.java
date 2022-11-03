package StepDefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.MyProfilePage;

import static libs.DriverHelper.getWebDriver;

public class Profile_StepDefinition {
    MyProfilePage myProfilePage = new MyProfilePage(getWebDriver());


    @Then("^User is redirected to 'Profile' page$")
        public void userIsRedirectToProfilePage(){
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}



