package StepDefinitions;

import Pages.MainPagePrivatBank;
import apiPrivatBank.ApiHelperPB;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class PrivatBankUI_StepDefinition {

    Logger logger = Logger.getLogger(getClass());

    MainPagePrivatBank mainPagePrivatBank = new MainPagePrivatBank(DriverHelper.getWebDriver());
    ApiHelperPB apiHelperPB = new ApiHelperPB();

    @When("^Open PrivatBank site and get '(.*)' curs$")
    public void openPrivatBankSiteAndGetCurrencyCurs(String currency) {
        mainPagePrivatBank.openMainPrivatBankPage();
        mainPagePrivatBank.getCurrencyValue(currency);


    }

    @Then("^Check that currency value is the same on UI and API$")
    public void checkThatCurrencyValueIsTheSameOnUIAndAPI() {
        Float curFromApiExpected = Float.parseFloat(ApiHelperPB.currencyApi.substring(0,7));
        Float curFromUIactual = Float.parseFloat(mainPagePrivatBank.getCurrencyUiValue());
        logger.info(ApiHelperPB.currencyApi);
        logger.info(mainPagePrivatBank.getUsdUiValue());
        logger.info("substring : " + ApiHelperPB.currencyApi.substring(0,4));
        Assert.assertEquals("Currency value does not match : ", curFromApiExpected , curFromUIactual);
    }
}
