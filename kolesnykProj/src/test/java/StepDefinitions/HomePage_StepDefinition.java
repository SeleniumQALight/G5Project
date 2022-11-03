package StepDefinitions;

import Pages.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;

public class HomePage_StepDefinition {

    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        homePage.openHomePage();
    }

    @When("^User click on 'MyProfile' button on 'Home' page$")
    public void userClickOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElements().clickOnButtonMyProfile();
    }
}
