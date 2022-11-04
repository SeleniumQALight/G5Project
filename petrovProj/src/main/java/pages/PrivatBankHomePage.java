package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PrivatBankHomePage extends ParentPagePrivatBank{

    private String rateCurrencyRowsLocator = ".//div[@data-cource_type='posts_course']//tbody//tr";
    private String CurrencyFromLocator = ".//div[@data-cource_type='posts_course']//tbody//tr[%s]/td";
    private String currencyToLocator = ".//div[@data-cource_type='posts_course']//tbody//tr[%s]/td[text()='%s']";
    private String currencyBuy = ".//td[@id='%s_buy']";
    private String currencySale = ".//td[@id='%s_sell']";

    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public PrivatBankHomePage openPrivateBankHomePage() {
            try {
                webDriver.get(basePrivatBankUrl);
                logger.info("PrivatBank page was opened");
                logger.info(basePrivatBankUrl);
            } catch (Exception e) {
                assertFailedLogger("Can not work with site" + e);
            }
        return new PrivatBankHomePage(webDriver);
    }


    private void assertFailedLogger(String textForPrint){
        logger.error(textForPrint);
        Assert.fail(textForPrint);
    }

    public void getCurrencyExchangeRates(String currencyFrom, String currencyTo) {
        try {
            for (int i = 1; i <(webDriver.findElements(By.xpath(rateCurrencyRowsLocator)).size()+1); i++) {
                    WebElement element = webDriver.findElement(By.xpath(String.format(CurrencyFromLocator, i)));
                    WebElement elementCurrencyTo = webDriver.findElement(By.xpath(String.format(currencyToLocator, i, currencyTo)));

                    if(element.getText().equalsIgnoreCase(currencyFrom) && elementCurrencyTo.getText().equalsIgnoreCase(currencyTo)){
                        TestData.CurrencyRateBuyFromUI = webDriver.findElement(By.xpath(String.format(currencyBuy, currencyFrom))).getText();
                        TestData.CurrencyRateSaleFromUI = webDriver.findElement(By.xpath(String.format(currencySale, currencyFrom))).getText();

                        System.out.println("UI "+currencyFrom +":"+currencyTo +" by "+TestData.CurrencyRateBuyFromUI+" sale "+TestData.CurrencyRateSaleFromUI);
                        break;
                    }else {
                        logger.info("there are no currency pairs: " + currencyFrom +", " + currencyTo);
                    }
                 }
        }catch (Exception e){
            logger.info("error " + e.getMessage());
        }

    }
}
