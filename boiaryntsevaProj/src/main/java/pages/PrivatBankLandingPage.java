package pages;

import api.BankEndPoints;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankLandingPage extends ParentPage {

    public PrivatBankLandingPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    String getRelativeUrl() {
        return null;
    }

    public PrivatBankLandingPage openLandingPage() {
        webDriver.get(BankEndPoints.UI_URL);
        return new PrivatBankLandingPage(webDriver);
    }

    public PrivatBankLandingPage getCurrencyRateOnUi(String currency) {
        WebElement currencyElementBuy= webDriver.findElement(By.id(currency+"_buy"));
        WebElement currencyElementSell= webDriver.findElement(By.id(currency+"_sell"));
            TestData.setUiCurrencyBuy(Float.parseFloat(currencyElementBuy.getText()));
            TestData.setUiCurrencySell(Float.parseFloat(currencyElementSell.getText()));
            logger.info("Exchange rate on UI for " + currency + " is Buy: " + TestData.getUiCurrencyBuy() + "and Sell: "
                    + TestData.getUiCurrencySell());
        return this;
    }

    public void checkIfCurrencyMatchesOnApiAndUi() {
        logger.info("API Sell " + TestData.getApiCurrencySell());
        logger.info("UI Sell " + TestData.getUiCurrencySell());
        logger.info("API Buy " + TestData.getApiCurrencyBuy());
        logger.info("UI Buy " + TestData.getUiCurrencyBuy());
        if (TestData.noCorrespondingCurrency == true) {
            logger.info("There is no sufficient rates provided");
        } else {
            Assert.assertEquals(TestData.getApiCurrencySell(), TestData.getUiCurrencySell(), 0);
            Assert.assertEquals(TestData.getApiCurrencyBuy(), TestData.getUiCurrencyBuy(), 0);
        }

    }


}
