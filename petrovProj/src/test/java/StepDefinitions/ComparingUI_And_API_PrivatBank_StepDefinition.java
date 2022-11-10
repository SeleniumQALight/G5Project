package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ComparingUI_And_API_PrivatBank_StepDefinition {
    protected Logger logger = Logger.getLogger(getClass());

    @Then("^User comparing saved rates for UI and Api for '(.*)' to '(.*)'$")
    public void userComparingSavedRatesForUIAndApiForCurrencyFromToCurrencyTo(String currencyFrom, String currencyTo) {

        try {
            SoftAssertions softAssertions = new SoftAssertions();

            softAssertions.assertThat(TestData.Currency_NameFrom_FromAPI).as("Currency name not equals ").isEqualTo(currencyFrom);
            softAssertions.assertThat(TestData.Currency_NameTo_FromApi).as("Currency name not equals ").isEqualTo(currencyTo);

            softAssertions.assertThat(TestData.CurrencyRateBuyFromAPI).as("Rates not equals ").isEqualTo(TestData.CurrencyRateBuyFromUI);
            softAssertions.assertThat(TestData.CurrencyRateSaleFromAPI).as("Rates not equals ").isEqualTo(TestData.CurrencyRateSaleFromUI);

            softAssertions.assertAll();
        }catch (Exception e){
            logger.info("Error " + e.getMessage());
        }

    }

}
