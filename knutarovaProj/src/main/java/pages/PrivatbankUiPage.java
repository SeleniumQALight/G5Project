package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatbankUiPage extends ParentPage {

    public PrivatbankUiPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public PrivatbankUiPage openPrivatbankUiPage() {
        try {
            webDriver.get("https://privatbank.ua");
            logger.info("PrivatbankUi Page was opened");
            logger.info("https://privatbank.ua");
        } catch (Exception e) {
            logger.error("Can't work with site");
            Assert.fail("Can't work with site");
        }
        return this;
    }

    public void saveCurrencyRateUiToTestData(String currency_buy, String currency_sale) {
        WebElement currency_to_buy = webDriver.findElement(By.id(String.format("%s_buy", currency_buy)));
        WebElement currency_to_sale = webDriver.findElement(By.id(String.format("%s_sell", currency_buy)));
        TestData.RATE_TO_BUY_UI = Float.valueOf(currency_to_buy.getText());
        TestData.RATE_TO_SALE_UI = Float.valueOf(currency_to_sale.getText());
        logger.info("From UI: rate to buy: " + TestData.RATE_TO_BUY_UI + "; " + "rate to sale: " + TestData.RATE_TO_SALE_UI);
    }

    public void checkExchangeRatesIsEquals() {
        Assert.assertEquals("Rate to buy is ", TestData.RATE_TO_BUY_API, TestData.RATE_TO_BUY_UI);
        Assert.assertEquals("Rate to sale is ", TestData.RATE_TO_SALE_API, TestData.RATE_TO_SALE_UI);
        logger.info("Rates currency to buy and sale are the same on UI and API");

    }

}
