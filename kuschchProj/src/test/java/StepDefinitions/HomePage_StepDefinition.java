package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;

import static libs.DriverHelper.getWebDriver;


public class HomePage_StepDefinition {
    HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        homePage.openHomePage();
    }

    @When("^User click on 'MyProfile' button on 'Login' page$")
    public void userClickOnMyProfileButtonOnLoginPage() {
        homePage.getHeaderElement().clickOnMyProfileButton();
    }

}