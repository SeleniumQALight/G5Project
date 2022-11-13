package StepDefinitions;

import apiPrivatBank.ApiHelperPB;
import cucumber.api.java.en.Given;

public class ApiPrivatBank_StepDefinition {
    ApiHelperPB apiHelperPB = new ApiHelperPB();

    @Given("^Store buy value for '(.*)' currency$")
    public void storeBuyValueForCurrencyCurrency(String currency) {
        apiHelperPB.storeCurrencyValue(currency);
    }
}
