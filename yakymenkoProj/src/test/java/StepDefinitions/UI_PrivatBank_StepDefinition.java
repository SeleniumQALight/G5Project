package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PrivatBankHomePage;

import static libs.DriverHelper.getWebDriver;

public class UI_PrivatBank_StepDefinition {
    PrivatBankHomePage privatBankHomePage = new PrivatBankHomePage(getWebDriver());

    @When("^User opens Home page on site PrivatBank$")
    public void userOpensHomePageOnSitePrivatBank() {
        privatBankHomePage.openHomePage();
    }

    @Then("^User receives the purchase and sale rates through the PrivatBank UI for the currency '(.*)' and saves them$")
    public void userReceivesThePurchaseAndSaleRatesThroughThePrivatBankUIForTheCurrencyAndSavesThem(String currency) {
        privatBankHomePage.getExchangeRateByCurrency(currency);
    }

    @Then("^Compare purchase and sale exchange rates for a currency pair via API and UI$")
    public void compareBuyAndSellExchangeRatesForACurrencyPairViaAPIAndUI() {
        privatBankHomePage.checkSameCourse();
    }
}