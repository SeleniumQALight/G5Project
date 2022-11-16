package privatePage;

import api.EndpointsPrivate;
import libs.DataPrivate;
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
        WebElement buyRate_UI = webDriver.findElement(By.id(currency +"_buy"));
        WebElement saleRate_UI = webDriver.findElement(By.id(currency + "_sell"));
      DataPrivate.BUY_UI = Float.valueOf(buyRate_UI.getText());
      DataPrivate.SALE_UI = Float.valueOf(saleRate_UI.getText());

        logger.info(currency+" UI buy rate is " + DataPrivate.BUY_UI + " and sale rate is " + DataPrivate.SALE_UI);
    }
    public void checkRatesEquality() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(DataPrivate.BUY_UI).isEqualTo(DataPrivate.BUY_API);
        softAssertions.assertThat(DataPrivate.SALE_UI).isEqualTo(DataPrivate.SALE_API);
        softAssertions.assertAll();
        logger.info("Currency exchange rates are the same on UI and API");
    }
}
