package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.HomePage;

public class HomePage_StepDefinition {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("User sees avatar icon")
    public void userSeesAvatarIcon() {
        homePage.checkIsIconVisible();
    }

}
