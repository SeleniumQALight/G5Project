package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinition {
LoginPage loginPage = new LoginPage(getWebDriver());
HomePage homePage = new HomePage(getWebDriver());
    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        homePage.openHomePage();
    }

    @When("^User click on 'MyProfile' button on 'Home' page$")
    public void userClickOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElement().clickOnMyProfileButton();
    }


    @Then("User sees profile avatar")
    public void userSeesProfileAvatar() {
        homePage.isUserAvatarDisplayed();
    }
}
