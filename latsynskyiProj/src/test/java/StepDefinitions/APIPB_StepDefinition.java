package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.Given;

public class APIPB_StepDefinition {
    ApiHelper apiHelper = new ApiHelper();

    @Given("^Check'(.*)'via API for PB and save value$")
    public void checkCurrencyViaAPIForPB(String currency) {
        apiHelper.getCurrencyPBByAPI(currency);

    }
}
