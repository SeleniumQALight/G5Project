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

public class PrivatHomePage {
    private final String BASE_URL = "https://privatbank.ua/";
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public PrivatHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void openHomePage(){
            try{
                webDriver.get(BASE_URL);
                logger.info("Home page was opened");
                logger.info(BASE_URL);
            }catch (Exception e){
                logger.error("Can not work with site");
                Assert.fail("Can not work with site");
            }
        }
    public static Float rounding(Float value){
        BigDecimal bigDecimal = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        return Float.valueOf(String.valueOf(bigDecimal));
    }
    public void getExchangeRateByCurrency(String currency) {
        WebElement buyRate = webDriver.findElement(By.id(String.format("%s_buy", currency)));
        WebElement saleRate = webDriver.findElement(By.id(String.format("%s_sell", currency)));
        TestData.RATE_BUY_UI = rounding(Float.valueOf(buyRate.getText()));
        TestData.RATE_SALE_UI = rounding(Float.valueOf(saleRate.getText()));

        logger.info("Buy rate on UI is " + TestData.RATE_BUY_UI + " and sale rate on UI is " + TestData.RATE_SALE_UI);
    }

    public void checkRatesEquality() {
        Assert.assertEquals("Buy rate is ", TestData.RATE_BUY_API, TestData.RATE_BUY_UI);
        Assert.assertEquals("Sale rate is ", TestData.RATE_SALE_API, TestData.RATE_SALE_UI);
        logger.info("Buy and sale exchange rates are the same on UI and API");
    }
}
