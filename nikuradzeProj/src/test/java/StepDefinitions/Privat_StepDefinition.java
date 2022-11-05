package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.PrivatHomePage;

public class Privat_StepDefinition {
    PrivatHomePage privatHomePage = new PrivatHomePage(DriverHelper.getWebDriver());

    @When("^User opens privatbank Home page$")
    public void openPrivatHomePage(){
        privatHomePage.openHomePage();
    }
    @Then("^User gets buy and sell exchange rates from UI for '(.*)' currency and save$")
    public void getExchangeRatesFromUiAndSave(String currency){
        privatHomePage.getExchangeRateByCurrency(currency);
    }
    @Then("^Check if buy and sell exchange rates for currency pair from API and UI are equal$")
    public void checkIfRatesEqual() {
        privatHomePage.checkRatesEquality();
    }
}
