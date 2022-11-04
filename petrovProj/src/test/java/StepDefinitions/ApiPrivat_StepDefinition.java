package StepDefinitions;

import api.ApiHelper;
import api.PrivatHW.ExchangeCoursResponseDTO;
import cucumber.api.java.en.Given;
import libs.TestData;
import org.apache.log4j.Logger;


public class ApiPrivat_StepDefinition {
    ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());

    @Given("^User send API and save currency rates from API '(.*)' to '(.*)'$")
    public void getCurrencyCoursFromAPICurrencyFromToCurrencyTo(String currencyFrom, String currencyTo) {
        ExchangeCoursResponseDTO[] response = apiHelper.getExchangeRateByPrivat24ForComparing();

        for (int i = 0; i < response.length; i++) {
            if(response[i].getCcy().equalsIgnoreCase(currencyFrom) && response[i].getBase_ccy().equalsIgnoreCase(currencyTo)){

                TestData.Currency_NameFrom_FromAPI = response[i].getCcy();
                TestData.Currency_NameTo_FromApi = response[i].getBase_ccy();

                TestData.CurrencyRateBuyFromAPI = apiHelper.decimalFormatCurrency(response[i].getBuy());
                TestData.CurrencyRateSaleFromAPI = apiHelper.decimalFormatCurrency(response[i].getSale());

                break;
            }else {
                logger.info("there are no currency pairs: " + currencyFrom +", " + currencyTo);
            }
        }

        System.out.println("----------------");
        System.out.println("API "+
                TestData.Currency_NameFrom_FromAPI + ":" + TestData.Currency_NameTo_FromApi
                        + " by " + TestData.CurrencyRateBuyFromAPI
                        + " sale " + TestData.CurrencyRateSaleFromAPI
        );
        System.out.println("_________________");

    }

}
