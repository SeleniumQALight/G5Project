package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import org.junit.Assert;
import pages.elements.HeaderElement;

public class Header_StepDefinition {
    HeaderElement headerElement = new HeaderElement(DriverHelper.getWebDriver());

    @Then("^User sees profile avatar$")
    public void user_sees_a_profile_avatar() {
        Assert.assertTrue("Profile avatar is not displayed", headerElement.isButtonMyProfileDisplayed());
    }
}
