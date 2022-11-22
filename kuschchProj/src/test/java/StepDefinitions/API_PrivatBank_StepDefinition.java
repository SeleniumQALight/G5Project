package StepDefinitions;

import api.ApiHelperPrivatBank;
import api.CurrencyExchangePrivatBankDTO;
import cucumber.api.java.en.Given;
import libs.TestData;
import org.apache.log4j.Logger;
import pages.PrivatBankHomePage;


public class API_PrivatBank_StepDefinition {
    Logger logger = Logger.getLogger(getClass());

    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Given("^User received buying and selling rates of currencies through '(.*)' API$")
    public void user_received_buying_and_selling_rates_of_currencies_through_currency_API(String currency) {
        CurrencyExchangePrivatBankDTO[] responce = apiHelperPrivatBank.getExchangeRatesAPI(5);
        for (int i = 0; i < responce.length; i++) {
            if (responce[i].getCcy().equalsIgnoreCase(currency)){
                TestData.EXCHANGE_RATES_BUY_API = PrivatBankHomePage.decimals(responce[i].getBuy());
                TestData.EXCHANGE_RATES_SALE_API = PrivatBankHomePage.decimals(responce[i].getSale());
                logger.info("From API: Currency " + currency + " buy rate is " + TestData.EXCHANGE_RATES_BUY_API +
                        " and sell rate is " + TestData.EXCHANGE_RATES_SALE_API);
            }
        }
    }

 }