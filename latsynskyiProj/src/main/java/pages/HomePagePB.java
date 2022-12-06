package pages;

import api.EndPointsPB;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePagePB extends ParentPagePB {
    public String Buy = ".//td[@id='%s_buy']";
    private String Sale = ".//td[@id='%s_sell']";
    private String CurrencyName = ".//div[@data-cource_type='posts_course']//tbody//tr/td[text()='%s']";
 public HomePagePB(WebDriver webDriver) {
        super(webDriver);
    }
    public void openHomePagePB(String currency) {

        try {
            webDriver.get(EndPointsPB.baseUrlUIPrivatBank);
            logger.info("Home page PB was opened");
            logger.info(EndPointsPB.baseUrlUIPrivatBank);
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        TestData.CURRENCYNAME_FROM_UI=webDriver.findElement(By.xpath(String.format(CurrencyName,currency))).getText();
        logger.info("CURRENCYNAME_FROM_UI______________________"+TestData.CURRENCYNAME_FROM_UI);
        TestData.CURRENCYBUY_FROM_UI=webDriver.findElement(By.xpath(String.format(Buy,currency))).getText();
        logger.info("CURRENCY BUY FROM UI______________________"+TestData.CURRENCYBUY_FROM_UI);
        TestData.CURRENCYSALE_FROM_UI=webDriver.findElement(By.xpath(String.format(Sale,currency))).getText();
        logger.info("CURRENCY SALE FROM UI______________________"+TestData.CURRENCYSALE_FROM_UI);


    }
public void checkCurrency (){
    try {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(TestData.CURRENCY_NAME_FROM_API).as("Different value Name carrency----").isEqualTo(TestData.CURRENCYNAME_FROM_UI);
        logger.info("CURRENCYNAME_FROM_API--------"+TestData.CURRENCY_NAME_FROM_API +"    CURRENCYNAME_FROM_UI--------"+TestData.CURRENCYNAME_FROM_UI);
        softAssertions.assertThat(TestData.CURRENCY_BUY_FROM_API).as("Different value BUY----"+TestData.CURRENCYNAME_FROM_UI).isEqualTo(TestData.CURRENCYBUY_FROM_UI);
        logger.info("CURRENCYBUY_FROM_API--------"+TestData.CURRENCY_BUY_FROM_API +"    CURRENCYBUY_FROM_UI--------"+TestData.CURRENCYBUY_FROM_UI);
        softAssertions.assertThat(TestData.CURRENCY_SALE_FROM_API).as("Different value SALE----"+TestData.CURRENCYNAME_FROM_UI).isEqualTo(TestData.CURRENCYSALE_FROM_UI);
        logger.info("CURRENCYSALE_FROM_API--------"+TestData.CURRENCY_SALE_FROM_API +"     CURRENCYSALE_FROM_UI--------"+TestData.CURRENCYSALE_FROM_UI);

        softAssertions.assertAll();
    }catch (Exception e){
        logger.info("Error " + e.getMessage());
    }
}


}
