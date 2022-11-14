package StepDefinitions;

import api.ApiHelperForPrivatbank;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.PrivatbankUiPage;

public class PrivatbankApi_StepDefinition {
    ApiHelperForPrivatbank apiHelperForPrivatbank = new ApiHelperForPrivatbank();
    PrivatbankUiPage privatbankUiPage = new PrivatbankUiPage(DriverHelper.getWebDriver());

    @Given("^User save currency rate (.*) to (.*) from PrivatbankApi to TestData$")
    public void user_save_currency_rate_from_PrivatbankApi_to_TestData(String currency_to_exchange, String base_currency) {
        apiHelperForPrivatbank.getAndSaveCourseExchangeForCompareFromPrivatbank(currency_to_exchange, base_currency);
    }

    @When("^User opens 'PrivatbankUi' Page and save currency rate (.*) to (.*) to TestData$")
    public void user_opens_PrivatbankUi_Page_and_save_currency_rate_to_TestData(String currency_buy, String currency_sale) {
        privatbankUiPage.openPrivatbankUiPage();
        privatbankUiPage.saveCurrencyRateUiToTestData(currency_buy, currency_sale);
    }

    @Then("^User compare currency rates from PrivatbankApi and 'PrivatbankUi' Page$")
    public void user_compare_currency_rates_from_PrivatbankApi_PrivatbankUi_Page() {
        privatbankUiPage.checkExchangeRatesIsEquals();
    }
}

