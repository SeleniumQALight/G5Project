package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.PrivatBankHomePage;

public class HomePagePrivatBank_StepDefinition {
    PrivatBankHomePage privatBankHomePage = new PrivatBankHomePage(DriverHelper.getWebDriver());

    @When("^Open privat bank 'home' page$")
    public void openPrivatBankHomePage() {
        privatBankHomePage.openPrivateBankHomePage();
    }

    @And("^User save currency rates from UI for '(.*)' to '(.*)'$")
    public void userSaveCurrencyRatesFromUIForCurrencyFromToCurrencyTo(String currencyFrom, String currencyTo) {
        privatBankHomePage.getCurrencyExchangeRates(currencyFrom, currencyTo);
    }
}
