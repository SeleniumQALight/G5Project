package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;

public class HomePage_StepDefinition {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User sees avatar icon on 'Home' page$")
    public void userSeesAvatarIcon() {
        homePage.checkIsIconVisible();
    }

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        homePage.openHomePage();
    }

    @When("^User click on 'MyProfile' button on 'Home' page$")
    public void userClickOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElement().clickOnMyProfileButton();
    }
}
