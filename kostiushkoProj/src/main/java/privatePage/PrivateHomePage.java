package privatePage;

import api.EndpointsPrivate;
import libs.dataPrivate;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class PrivateHomePage {


    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public PrivateHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openPrivatHomePage(){
        try{
            webDriver.get(EndpointsPrivate.BASE_URL);
            logger.info("Home page was opened " + EndpointsPrivate.BASE_URL);
        }catch (Exception e){
            Assert.fail("Site not work");
        }
    }
    public void getValuesRateByCurrencyViaUI(String currency) {
        WebElement buyRate_UI = webDriver.findElement(By.id(String.format(currency +"_buy")));
        WebElement saleRate_UI = webDriver.findElement(By.id(String.format(currency + "_sell")));
      dataPrivate.BUY_UI = Float.valueOf(buyRate_UI.getText());
      dataPrivate.SALE_UI = Float.valueOf(saleRate_UI.getText());

        logger.info(currency+" UI buy rate is " + dataPrivate.BUY_UI + " and sale rate is " + dataPrivate.SALE_UI);
    }

    public void checkRatesEquality() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(dataPrivate.BUY_UI.equals(dataPrivate.BUY_API));
        softAssertions.assertThat(dataPrivate.SALE_UI.equals(dataPrivate.SALE_API));
        softAssertions.assertAll();
        logger.info("Currency exchange rates are the same on UI and API");
    }
}