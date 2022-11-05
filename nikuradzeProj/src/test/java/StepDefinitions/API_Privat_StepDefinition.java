package StepDefinitions;

import api.ApiHelperPrivat;
import cucumber.api.java.en.Given;

public class API_Privat_StepDefinition {
    ApiHelperPrivat apiHelperPrivat = new ApiHelperPrivat();

    @Given("^User gets buy and sell exchange rates via API for '(.*)' currency and save$")
    public void getExchangeRatesFromApiAndSave(String currency){
        apiHelperPrivat.getExchangeRateByCurrency(currency);
    }
}
