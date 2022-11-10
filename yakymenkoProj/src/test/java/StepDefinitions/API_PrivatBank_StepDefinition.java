package StepDefinitions;

import api.ApiHelperPrivatBank;
import cucumber.api.java.en.Given;

public class API_PrivatBank_StepDefinition {
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Given("^User receives the purchase and sale rates through the PrivatBank API for the currency '(.*)' and saves them$")
    public void userReceivesThePurchaseAndSaleRatesThroughThePrivatBankUIForTheCurrencyAndSavesThem(String currency) {
        apiHelperPrivatBank.getExchangeRateByCurrency(currency);
    }
}