package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.elements.HeaderElement;

public class HeaderElement_StepDefinition {
    HeaderElement headerElement = new HeaderElement(DriverHelper.getWebDriver());

    @Then("^User sees profile avatar$")
    public void user_sees_profile_avatar() {
        headerElement.isButtonMyProfileDisplayed();
    }
}
