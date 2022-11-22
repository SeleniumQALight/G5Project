package pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrivatBankHomePage {
    private final String PRIVATBANK_URL = "https://privatbank.ua/";
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public PrivatBankHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public static Float decimals(Float fraction){
        BigDecimal bigDecimal = BigDecimal.valueOf(fraction).setScale(2, RoundingMode.HALF_UP);
        return Float.valueOf(String.valueOf(bigDecimal));
    }

    public void openPrivatBankHomePage(){
        try{
            webDriver.get(PRIVATBANK_URL);
            logger.info("Privat Bank Home page was opened");
        }catch (Exception e){
            logger.error("Privat Bank Home page wasn`t opened");
        }
    }

    public void getExchangeRatesUI(String currency) {
        WebElement buyCurrency = webDriver.findElement(By.id(String.format("%s_buy", currency)));
        WebElement saleCurrency = webDriver.findElement(By.id(String.format("%s_sell", currency)));
        TestData.EXCHANGE_RATES_BUY_UI = decimals(Float.valueOf(buyCurrency.getText()));
        TestData.EXCHANGE_RATES_SALE_UI = decimals(Float.valueOf(saleCurrency.getText()));
        logger.info("Buy rate on the UI: " + TestData.EXCHANGE_RATES_BUY_UI + "sale rate on the UI: " + TestData.EXCHANGE_RATES_SALE_UI);
    }

    public void checkCurrenciEqual() {
        Assert.assertEquals("Buy rate is ", TestData.EXCHANGE_RATES_BUY_API, TestData.EXCHANGE_RATES_BUY_UI);
        Assert.assertEquals("Sale rate is ", TestData.EXCHANGE_RATES_SALE_API, TestData.EXCHANGE_RATES_SALE_UI);
        logger.info("The buying and selling currency rates are the same on the UI and API");
    }

}
