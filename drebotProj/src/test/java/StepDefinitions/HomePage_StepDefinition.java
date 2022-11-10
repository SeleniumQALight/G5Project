package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinition {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User goes to 'Home' page and button 'SignOut' is displayed$")
    public void user_goes_to_Home_page_and_button_SignOut_is_displayed() {
        homePage.checkIsAvatarDisplayed();
    }

    @Given("^User opens 'Home' page$")
    public void user_opens_home_page() {
        homePage.openHomePage();
    }

    @When("^User click on 'MyProfile' button on 'Home' page$")
    public void userClickOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElement().clickOnButtonMyProfile();
    }
}
