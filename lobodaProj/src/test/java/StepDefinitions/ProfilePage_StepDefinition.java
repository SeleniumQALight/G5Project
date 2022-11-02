package StepDefinitions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.MyProfilePage;


public class ProfilePage_StepDefinition {
    MyProfilePage myProfilePage = new MyProfilePage(DriverHelper.getWebDriver());


    @Then("User is redirected to 'Profile' page")
    public void userIsRedirectedToProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
