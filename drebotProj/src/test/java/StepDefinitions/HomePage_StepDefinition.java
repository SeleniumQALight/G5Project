package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.HomePage;

public class HomePage_StepDefinition {

    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User goes to 'Home' page and button 'SignOut' is displayed$")
    public void user_goes_to_Home_page_and_button_SignOut_is_displayed() {
        homePage.checkIsAvatarDisplayed();
    }

}
