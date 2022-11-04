package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

public class ComparingUI_And_API_PrivatBank_StepDefinition {
    protected Logger logger = Logger.getLogger(getClass());

    @Then("^User comparing saved rates for UI and Api for '(.*)' to '(.*)'$")
    public void userComparingSavedRatesForUIAndApiForCurrencyFromToCurrencyTo(String currencyFrom, String currencyTo) {

        try {
            SoftAssertions softAssertions = new SoftAssertions();

            Assert.assertEquals("Rates not equals ",TestData.Currency_NameFrom_FromAPI, currencyFrom);
            Assert.assertEquals("Rates not equals ",TestData.Currency_NameTo_FromApi, currencyTo);

            Assert.assertEquals("Rates not equals ",TestData.CurrencyRateBuyFromAPI, TestData.CurrencyRateBuyFromUI);
            Assert.assertEquals("Rates not equals ",TestData.CurrencyRateSaleFromAPI, TestData.CurrencyRateSaleFromUI);

            softAssertions.assertAll();
        }catch (Exception e){
            logger.info("Error " + e.getMessage());
        }

    }

}
