package StepDefinitions;

import api.PrivatBankApiHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.PrivatBankLandingPage;

public class CurrencyExchange_StepDefinition {
    PrivatBankLandingPage privatBankLandingPage = new PrivatBankLandingPage(DriverHelper.getWebDriver());
    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();

    @Given("^'(.*)' exchange rate is received through API$")
    public void currencyExchangeRateIsReceivedThroughAPI(String currency) {
        privatBankApiHelper.getExchangeRateForCurrencyThroughApi("5", currency);
    }

    @When("^User opens Privat bank landing page$")
    public void userOpensPrivatBankLandingPage() {
        privatBankLandingPage.openLandingPage();
    }

    @Then("^'(.*)' exchange rates on UI matches exchange from API$")
    public void currencyExchangeRatesOnUIMatchesCurrencyExchangeFromAPIForAllCurrencies(String currency) {
        privatBankLandingPage.getCurrencyRateOnUi(currency);
        privatBankLandingPage.checkIfCurrencyMatchesOnApiAndUi();

        System.out.println("YAY");
    }
}
