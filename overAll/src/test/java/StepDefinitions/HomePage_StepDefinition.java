package StepDefinitions;

import static libs.DriverHelper.getWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;

public class HomePage_StepDefinition {
    HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        homePage.openHomePage();
    }

    @When("^User click on 'MyProfile' button on 'Home' page$")
    public void userClickOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElement().clickOnMyProfileButton();
    }
}
