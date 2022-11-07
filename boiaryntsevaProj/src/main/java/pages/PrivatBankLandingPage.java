package pages;

import api.BankEndPoints;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankLandingPage extends ParentPage {

    @FindBy(xpath = ".//*[@id='EUR_buy']")
    private WebElement euroBuy;

    @FindBy(xpath = ".//*[@id='EUR_sell']")
    private WebElement euroSell;

    @FindBy(xpath = ".//*[@id='USD_buy']")
    private WebElement usdBuy;

    @FindBy(xpath = ".//*[@id='USD_sell']")
    private WebElement usdSell;


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
        if (currency.equalsIgnoreCase("USD")) {
            TestData.setUiCurrencyBuy(Float.parseFloat(usdBuy.getText()));
            TestData.setUiCurrencySell(Float.parseFloat(usdSell.getText()));
            logger.info("Exchange rate on UI for " + currency + " is Buy: " + TestData.getUiCurrencyBuy() + "and Sell: "
                    + TestData.getUiCurrencySell());
        } else if (currency.equalsIgnoreCase("EUR")) {
            TestData.setUiCurrencyBuy(Float.parseFloat(euroBuy.getText()));
            TestData.setUiCurrencySell(Float.parseFloat(euroSell.getText()));
            logger.info("Exchange rate on UI for " + currency + " is Buy: " + TestData.getUiCurrencyBuy() + "and Sell: "
                    + TestData.getUiCurrencySell());
        } else {
            TestData.noCorrespondingCurrency = true;
            logger.info("No such currency on UI");
        }
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
