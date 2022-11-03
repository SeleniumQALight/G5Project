package StepDefinitions;

import api.ApiHelper;
import api.PrivatHW.ExchangeCoursResponseDTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.TestData;

public class ApiPrivat_StepDefinition {
    ApiHelper apiHelper = new ApiHelper();

    @Given("^User send API and save currency rates from API '(.*)' to '(.*)'$")
    public void getCurrencyCoursFromAPICurrencyFromToCurrencyTo(String currencyFrom, String currencyTo) {

        ExchangeCoursResponseDTO[] response = apiHelper.getExchangeRateByPrivat24();

        for (int i = 0; i < response.length; i++) {
            if(response[i].getCcy().equalsIgnoreCase(currencyFrom) && response[i].getBase_ccy().equalsIgnoreCase(currencyTo)){

                TestData.CurrencyFromName = response[i].getCcy();
                TestData.CurrencyToName = response[i].getBase_ccy();

                TestData.CurrencyRateBuyFromAPI = response[i].getBuy();
                TestData.CurrencyRateSaleFromAPI = response[i].getSale();
            }
        }

        System.out.println("----------------");
        System.out.println(TestData.CurrencyFromName);
        System.out.println(TestData.CurrencyToName);
        System.out.println(TestData.CurrencyRateBuyFromAPI);
        System.out.println(TestData.CurrencyRateSaleFromAPI);
        System.out.println("_________________");

    }

}
