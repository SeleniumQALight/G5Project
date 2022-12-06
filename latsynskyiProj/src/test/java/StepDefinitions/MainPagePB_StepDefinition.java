package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePagePB;

import static libs.DriverHelper.getWebDriver;

public class MainPagePB_StepDefinition {
HomePagePB homePagePB = new HomePagePB(getWebDriver());

    @When("^User open 'Home' page '(.*)' privat bank and save value$")
    public void userOpenHomePagePrivatBank(String currency)
    {
        homePagePB.openHomePagePB(currency);
    }

    @Then("Check via API for currency on UI")
    public void checkCurrencyViaAPIForCurrencyOnUI()
    {
      homePagePB.checkCurrency();
    }
}