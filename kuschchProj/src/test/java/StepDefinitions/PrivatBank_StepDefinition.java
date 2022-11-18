package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.PrivatBankHomePage;

public class PrivatBank_StepDefinition {
    PrivatBankHomePage privatBankHomePage = new PrivatBankHomePage(DriverHelper.getWebDriver());

    @When("^User opened privatBank Home page$")
    public void user_Opened_PrivatBank_HomePage() {
        privatBankHomePage.openPrivatBankHomePage();
    }

    @And("^User received buying and selling rates of currencies through '(.*)' UI$")
    public void user_Received_Buying_Selling_Rates_Currencies_Through_Currency_UI(String currency) {
        privatBankHomePage.getExchangeRatesUI(currency);
    }

    @Then("Checking for equality of buying and selling rates of currencies: API and UI")
    public void checking_Equality_Buying_Selling_Rates_Currencies_API_And_UI() {
        privatBankHomePage.checkCurrenciEqual();
    }
}